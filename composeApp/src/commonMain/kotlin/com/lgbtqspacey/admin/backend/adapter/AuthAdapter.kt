package com.lgbtqspacey.admin.backend.adapter

import com.lgbtqspacey.admin.backend.model.ApiResult
import com.lgbtqspacey.admin.backend.model.Confirmation
import com.lgbtqspacey.admin.backend.model.Login
import com.lgbtqspacey.admin.backend.router.AuthRouter
import com.lgbtqspacey.admin.backend.router.Backend
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.error_logging_out
import com.lgbtqspacey.admin.commonMain.composeResources.invalid_credentials
import com.lgbtqspacey.admin.commonMain.composeResources.something_went_wrong
import com.lgbtqspacey.admin.commonMain.composeResources.user_not_found
import com.lgbtqspacey.admin.database.Database
import com.lgbtqspacey.admin.database.model.Session
import com.lgbtqspacey.admin.getPlatform
import io.github.aakira.napier.Napier
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import io.sentry.kotlin.multiplatform.Sentry
import org.jetbrains.compose.resources.getString

class AuthAdapter {
    private val sessionDB = Database().session

    suspend fun login(login: Login): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))
        try {
            val response = AuthRouter.login(login, getPlatform().name)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    val token = response.headers[Backend.Headers.SESSION_TOKEN]
                    val expiration = response.headers[Backend.Headers.SESSION_EXPIRATION]
                    val userId = response.headers[Backend.Headers.SESSION_USER_ID]

                    if (token.isNullOrEmpty() || expiration.isNullOrEmpty() || userId.isNullOrEmpty()) {
                        return result
                    } else {
                        val session = Session(token, expiration, userId)
                        val writeToDatabase = sessionDB.createSession(session)

                        if (writeToDatabase) {
                            val confirmation = sendConfirmation(token, userId)
                            if (confirmation === null) {
                                return result
                            } else {
                                val userInfo = Session(
                                    name = confirmation.name,
                                    accessLevel = confirmation.accessLevel,
                                    pronouns = confirmation.pronouns,
                                )
                                sessionDB.setUserInfo(userInfo)
                            }
                        } else {
                            return result
                        }
                    }
                }

                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = HttpStatusCode.NotFound.value,
                        errorMessage = getString(Res.string.user_not_found),
                    )
                }

                HttpStatusCode.BadRequest -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = HttpStatusCode.BadRequest.value,
                        errorMessage = getString(Res.string.invalid_credentials),
                    )
                }
            }
            return result
        } catch (exception: Exception) {
            Napier.e("AuthAdapter :: Login", exception)
            Sentry.captureException(exception)
            return result
        }
    }

    suspend fun logout(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDB.getSession()

            val response = AuthRouter.logout(session.token)

            if (response?.status == HttpStatusCode.OK) {
                sessionDB.deleteSession()
                result = ApiResult(true)
            } else {
                result = ApiResult(
                    isSuccess = false,
                    errorCode = response?.status?.value,
                    errorMessage = getString(Res.string.error_logging_out)
                )
            }
            return result
        } catch (exception: Exception) {
            Napier.e("AuthAdapter :: Logout", exception)
            Sentry.captureException(exception)
            return result
        }
    }

    suspend fun logoutAllDevices(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDB.getSession()

            val response = AuthRouter.logoutAllDevices(session.userId)

            if (response?.status == HttpStatusCode.OK) {
                sessionDB.deleteSession()
                result = ApiResult(true)
            } else {
                result = ApiResult(
                    isSuccess = false,
                    errorCode = response?.status?.value,
                    errorMessage = getString(Res.string.error_logging_out)
                )
            }
            return result
        } catch (exception: Exception) {
            Napier.e("AuthAdapter :: LogoutAllDevices", exception)
            Sentry.captureException(exception)
            return result
        }
    }

    private suspend fun sendConfirmation(token: String, userId: String): Confirmation? {
        var result: Confirmation? = null
        try {
            val confirmation = AuthRouter.loginConfirmation(token, userId)

            if (confirmation?.status == HttpStatusCode.OK) {
                result = confirmation.body()
            } else {
                return null
            }
        } catch (exception: Exception) {
            Napier.e("AuthAdapter :: Login :: Confirmation", exception)
            Sentry.captureException(exception)
        }
        return result
    }
}
