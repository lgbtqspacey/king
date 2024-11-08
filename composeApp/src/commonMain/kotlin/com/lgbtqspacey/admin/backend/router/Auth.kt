package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Login
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class Auth {
    companion object {
        suspend fun login(password: String, emailOrUsername: String): HttpResponse {
            val identifier = if (emailOrUsername.contains("@")) {
                "email"
            } else {
                "username"
            }

            return Endpoints.CLIENT.request(Endpoints.Auth.LOGIN) {
                url.appendPathSegments("auth", "login")
                method = Post
                contentType(ContentType.Application.Json)
                setBody(Login(password, identifier))
            }
        }

        suspend fun teste(): String {
            val response = Endpoints.CLIENT.request("https://alt-bichinhos.onrender.com/api/image/all") {
                method = Get
            }

            return response.bodyAsText()
        }
    }
}
