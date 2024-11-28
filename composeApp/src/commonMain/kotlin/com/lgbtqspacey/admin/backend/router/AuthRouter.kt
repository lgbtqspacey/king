package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Login
import io.github.aakira.napier.Napier
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.sentry.kotlin.multiplatform.Sentry

class AuthRouter {
    companion object {
        /**
         * Creates an user session on the server.
         * @return session info on headers
         */
        suspend fun login(login: Login, deviceOs: String): HttpResponse? {
            try {
                return Backend.CLIENT.post(Backend.Routes.Auth.LOGIN) {
                    contentType(ContentType.Application.Json)
                    setBody(login)
                    headers.append(Backend.Headers.SESSION_DEVICE_OS, deviceOs)
                }
            } catch (exception: Exception) {
                Napier.e("AuthRouter :: Login", exception)
                Sentry.captureException(exception)
                return null
            }
        }

        /**
         * Sends login confirmation to the server with the session info.
         */
        suspend fun loginConfirmation(sessionToken: String, sessionUserId: String): HttpResponse? {
            try {
                return Backend.CLIENT.post(Backend.Routes.Auth.LOGIN) {
                    url.appendPathSegments("confirmation")
                    contentType(ContentType.Application.Json)
                    headers.append(Backend.Headers.SESSION_TOKEN, sessionToken)
                    headers.append(Backend.Headers.SESSION_USER_ID, sessionUserId)
                }
            } catch (exception: Exception) {
                Napier.e("AuthRouter :: Login", exception)
                Sentry.captureException(exception)
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
                Sentry.captureException(exception)
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
                Sentry.captureException(exception)
                return null
            }
        }
    }
}
