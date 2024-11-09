package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
)
