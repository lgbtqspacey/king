package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.LoginRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRouter {
    suspend fun login(username: String, password: String): HttpResponse? {
        try {
            val response = Backend.CLIENT.post(Backend.Routes.Auth.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(username, password))
            }

            return response
        } catch (exception: Exception) {
            println("AuthRouter :: Login => $exception")
            return null
        }
    }
}
