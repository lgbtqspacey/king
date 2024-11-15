package com.lgbtqspacey.admin.backend.adapter

import com.lgbtqspacey.admin.backend.model.ApiResult
import com.lgbtqspacey.admin.backend.model.Confirmation
import com.lgbtqspacey.admin.backend.router.AuthRouter
import com.lgbtqspacey.admin.backend.router.Backend
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.error_logging_out
import com.lgbtqspacey.admin.commonMain.composeResources.invalid_credentials
import com.lgbtqspacey.admin.commonMain.composeResources.something_went_wrong
import com.lgbtqspacey.admin.commonMain.composeResources.user_not_found
import com.lgbtqspacey.admin.database.api.TableSession
import com.lgbtqspacey.admin.getPlatform
import com.lgbtqspacey.database.Session
import io.github.aakira.napier.Napier
import io.ktor.http.HttpStatusCode
import org.jetbrains.compose.resources.getString

class AuthAdapter {
    suspend fun login(username: String, password: String): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))
        try {
            val response = AuthRouter().login(username, password)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    val sessionToken = response.headers[Backend.Headers.SESSION_TOKEN]
                    val expiresAt = response.headers[Backend.Headers.SESSION_EXPIRES_AT]
                    val userId = response.headers[Backend.Headers.SESSION_USER_ID]

                    if (sessionToken.isNullOrEmpty() || expiresAt.isNullOrEmpty() || userId.isNullOrEmpty()) {
                        return result
                    } else {
                        val session = Session(sessionToken, expiresAt, userId)
                        val writeToDatabase = TableSession(getPlatform().databaseDriver).createSession(session)

                        if (writeToDatabase) {
                            result = sendConfirmation(
                                Confirmation(
                                    sessionToken,
                                    expiresAt,
                                    userId,
                                    getPlatform().name,
                                    "local",
                                    "local"
                                )
                            )
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
            return result
        }
    }

    suspend fun logout(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val sessionDao = TableSession(getPlatform().databaseDriver)
            val session = sessionDao.getSession()

            val response = AuthRouter().logout(session.token)

            if (response?.status == HttpStatusCode.OK) {
                sessionDao.deleteSession()
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
            return result
        }
    }

    suspend fun logoutAllDevices(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val sessionDao = TableSession(getPlatform().databaseDriver)
            val session = sessionDao.getSession()

            val response = AuthRouter().logoutAllDevices(session.userId)

            if (response?.status == HttpStatusCode.OK) {
                sessionDao.deleteSession()
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
            return result
        }
    }

    private suspend fun sendConfirmation(confirmation: Confirmation): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))
        try {
            val confirmationResult = AuthRouter().loginConfirmation(confirmation)

            if (confirmationResult?.status == HttpStatusCode.OK) {
                result = ApiResult(true)
            } else {
                return result
            }
        } catch (exception: Exception) {
            Napier.e("AuthAdapter :: Login :: Confirmation", exception)
        }
        return result
    }
}
