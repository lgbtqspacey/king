package com.lgbtqspacey.admin.backend.router

class Endpoints {
    companion object {
        const val HOST = "http://localhost:3000"
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