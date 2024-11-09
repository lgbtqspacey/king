package com.lgbtqspacey.admin.backend.adapter

import admin_portal.composeapp.generated.resources.Res
import admin_portal.composeapp.generated.resources.error_logging_out
import admin_portal.composeapp.generated.resources.invalid_credentials
import admin_portal.composeapp.generated.resources.something_went_wrong
import admin_portal.composeapp.generated.resources.user_not_found
import com.lgbtqspacey.admin.backend.model.ApiResult
import com.lgbtqspacey.admin.backend.router.AuthRouter
import com.lgbtqspacey.admin.backend.router.Backend
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
                    // delete session from db
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

            result = if (response?.status == HttpStatusCode.OK) {
                // delete session from db
                ApiResult(true)
            } else {
                ApiResult(
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
