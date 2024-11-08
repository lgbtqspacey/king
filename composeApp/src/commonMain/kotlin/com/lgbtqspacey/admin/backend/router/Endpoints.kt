package com.lgbtqspacey.admin.backend.router

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

class Endpoints {
    companion object {
        const val HOST = "http://localhost:3000"
        val CLIENT = HttpClient(OkHttp)
    }

    object Auth {
        const val LOGIN = "$HOST/api/auth/login"
        const val LOGOUT = "$HOST/api/auth/logout"
    }

    object Admin {
        object Users {
            //
        }

        object Reports {
            //
        }

        object Roles {
            //
        }
    }
}
