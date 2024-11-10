package com.lgbtqspacey.admin.backend.adapter

import com.lgbtqspacey.admin.backend.model.ApiResult
import com.lgbtqspacey.admin.backend.router.AuthRouter
import com.lgbtqspacey.admin.backend.router.Backend
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.error_logging_out
import com.lgbtqspacey.admin.commonMain.composeResources.invalid_credentials
import com.lgbtqspacey.admin.commonMain.composeResources.something_went_wrong
import com.lgbtqspacey.admin.commonMain.composeResources.user_not_found
import com.lgbtqspacey.admin.helpers.Logger
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

                    // todo: save token

                    result = ApiResult(true)
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
        } catch (e: Exception) {
            Logger().error("AuthAdapter :: Login", e.toString())
            return result
        }
    }

    suspend fun logout(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            // todo: get session from db
            val sessionToken = ""

            val response = AuthRouter().logout(sessionToken)

            if (response?.status == HttpStatusCode.OK) {
                // todo: delete session from db
                result = ApiResult(true)
            } else {
                result = ApiResult(
                    isSuccess = false,
                    errorCode = response?.status?.value,
                    errorMessage = getString(Res.string.error_logging_out)
                )
            }
            return result
        } catch (e: Exception) {
            Logger().error("AuthAdapter :: Logout", e.toString())
            return result
        }
    }

    suspend fun logoutAllDevices(): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            // todo: get userId from db
            val userId = ""

            val response = AuthRouter().logoutAllDevices(userId)

            if (response?.status == HttpStatusCode.OK) {
                // todo: delete session from db
                result = ApiResult(true)
            } else {
                result = ApiResult(
                    isSuccess = false,
                    errorCode = response?.status?.value,
                    errorMessage = getString(Res.string.error_logging_out)
                )
            }
            return result
        } catch (e: Exception) {
            Logger().error("AuthAdapter :: LogoutAllDevices", e.toString())
            return result
        }
    }
}
