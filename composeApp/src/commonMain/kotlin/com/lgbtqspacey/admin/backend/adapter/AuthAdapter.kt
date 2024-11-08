package com.lgbtqspacey.admin.backend.adapter

import admin_portal.composeapp.generated.resources.Res
import admin_portal.composeapp.generated.resources.invalid_credentials
import admin_portal.composeapp.generated.resources.something_went_wrong
import admin_portal.composeapp.generated.resources.user_not_found
import com.lgbtqspacey.admin.backend.model.LoginResponse
import com.lgbtqspacey.admin.backend.router.AuthRouter
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import org.jetbrains.compose.resources.StringResource

class AuthAdapter {
    suspend fun login (username: String, password: String): Pair<Boolean, StringResource?> {
        val response = AuthRouter().login(username, password)

        return when (response?.status) {
            HttpStatusCode.OK -> {
                // save token
                val body = response.body<LoginResponse>().token

                return Pair(true, null)
            }

            HttpStatusCode.NotFound -> {
                return Pair(false, Res.string.user_not_found)
            }

            HttpStatusCode.BadRequest -> {
                return Pair(false, Res.string.invalid_credentials)
            }

            else -> {
                Pair(false, Res.string.something_went_wrong)
            }
        }
    }
}
