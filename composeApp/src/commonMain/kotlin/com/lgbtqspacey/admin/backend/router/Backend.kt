package com.lgbtqspacey.admin.backend.router

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Backend {
    companion object {
        const val HOST = "http://localhost:3000"

        /**
         * Setup Ktor client using OkHttp engine
         * @see HttpClient
         * @see OkHttp
         */
        val CLIENT = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
                sanitizeHeader {
                    it == HttpHeaders.Authorization ||
                        it == HttpHeaders.AuthenticationInfo ||
                        it.lowercase().contains("firebase")
                }
            }
        }
    }

    /**
     * Backend endpoints.
     *
     * All paths must have the api version prefix:
     * **e.g.**: `/api/v1/`
     */
    object Routes {
        object Auth {
            const val LOGIN = "$HOST/api/v1/auth/login"
            const val LOGOUT = "$HOST/api/v1/auth/logout"
        }

        object Admin {
            const val USERS = "$HOST/api/v1/admin/users"
            const val REPORTS = "$HOST/api/v1/admin/reports"
            const val ROLES = "$HOST/api/v1/admin/roles"
        }
    }

    object Headers {
        const val SESSION_TOKEN = "session-token"
        const val SESSION_EXPIRES_AT = "session-expires-at"
    }
}
