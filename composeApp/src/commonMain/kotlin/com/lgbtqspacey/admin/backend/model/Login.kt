package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
)

@Serializable
data class LoginResponse(
    val token: String? = null
)

data class LoginResult(
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
)
