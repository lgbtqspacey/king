package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Login
import io.ktor.client.*
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class Auth {
    private val client = HttpClient()

    suspend fun login(password: String, emailOrUsername: String): HttpResponse {
        val identifier = if (emailOrUsername.contains("@")) {
            "email"
        } else {
            "username"
        }

        return client.request(Endpoints.Auth.LOGIN) {
            url.appendPathSegments("auth", "login")
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(Login(password, identifier))
        }
    }

    private companion object {
        const val HOST = "http://localhost:3000"
    }
}