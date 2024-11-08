package com.lgbtqspacey.admin.backend.adapter

import admin_portal.composeapp.generated.resources.Res
import admin_portal.composeapp.generated.resources.invalid_credentials
import admin_portal.composeapp.generated.resources.something_went_wrong
import admin_portal.composeapp.generated.resources.user_not_found
import com.lgbtqspacey.admin.backend.model.LoginResponse
import com.lgbtqspacey.admin.backend.model.LoginResult
import com.lgbtqspacey.admin.backend.router.AuthRouter
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import org.jetbrains.compose.resources.getString

class AuthAdapter {
    suspend fun login (username: String, password: String): LoginResult {
        val response = AuthRouter().login(username, password)

        return when (response?.status) {
            HttpStatusCode.OK -> {
                // save token
                val body = response.body<LoginResponse>().token

                return LoginResult(true)
            }

            HttpStatusCode.NotFound -> {
                return LoginResult(false, getString(Res.string.user_not_found))
            }

            HttpStatusCode.BadRequest -> {
                return LoginResult(false, getString(Res.string.invalid_credentials))
            }

            else -> {
                LoginResult(false, getString(Res.string.something_went_wrong))
            }
        }
    }
}
