package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Login
import com.lgbtqspacey.admin.helpers.Logger
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
        } catch (e: Exception) {
            Logger().error("AuthRouter :: Login", e.toString())
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
        } catch (e: Exception) {
            Logger().error("AuthRouter :: Logout", e.toString())
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
        } catch (e: Exception) {
            Logger().error("AuthRouter :: LogoutAllDevices", e.toString())
            return null
        }
    }
}
