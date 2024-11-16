package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Confirmation
import com.lgbtqspacey.admin.backend.model.Login
import io.github.aakira.napier.Napier
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class AuthRouter {
    /**
     * Creates an user session on the server.
     * @return `session-token` and `session-expires-at` on headers
     */
    suspend fun login(username: String, password: String): HttpResponse? {
        try {
            return Backend.CLIENT.post(Backend.Routes.Auth.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(Login(username, password))
            }
        } catch (exception: Exception) {
            Napier.e("AuthRouter :: Login", exception)
            return null
        }
    }

    suspend fun loginConfirmation(confirmation: Confirmation): HttpResponse? {
        try {
            return Backend.CLIENT.post(Backend.Routes.Auth.LOGIN) {
                url.appendPathSegments("confirmation")
                contentType(ContentType.Application.Json)
                headers.append(Backend.Headers.SESSION_TOKEN, confirmation.sessionToken)
                headers.append(Backend.Headers.SESSION_USER_ID, confirmation.sessionUserId)
                headers.append(Backend.Headers.SESSION_EXPIRATION, confirmation.sessionExpiration)
                headers.append(Backend.Headers.SESSION_DEVICE_OS, confirmation.sessionDeviceOS)
            }
        } catch (exception: Exception) {
            Napier.e("AuthRouter :: Login", exception)
            return null
        }
    }

    /**
     * Deletes current user session from server.
     */
    suspend fun logout(sessionToken: String): HttpResponse? {
        try {
            return Backend.CLIENT.post(Backend.Routes.Auth.LOGOUT) {
                contentType(ContentType.Application.Json)
                headers.append(Backend.Headers.SESSION_TOKEN, sessionToken)
            }
        } catch (exception: Exception) {
            Napier.e("AuthRouter :: Logout", exception)
            return null
        }
    }

    /**
     * Deletes all user sessions from server.
     */
    suspend fun logoutAllDevices(userId: String): HttpResponse? {
        try {
            return Backend.CLIENT.post(Backend.Routes.Auth.LOGOUT) {
                url.appendPathSegments(userId)
                contentType(ContentType.Application.Json)
            }
        } catch (exception: Exception) {
            Napier.e("AuthRouter :: LogoutAllDevices", exception)
            return null
        }
    }
}
