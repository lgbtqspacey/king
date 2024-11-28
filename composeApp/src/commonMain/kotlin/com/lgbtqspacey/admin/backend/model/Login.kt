package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
)

@Serializable
data class Confirmation(
    val accessLevel: String,
    val name: String,
    val pronouns: String,
)
