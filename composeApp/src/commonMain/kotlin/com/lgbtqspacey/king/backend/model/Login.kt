package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
)

@Serializable
data class Confirmation(
    val _id: String,
    val accessLevel: String,
    val name: String,
    val pronouns: String,
)
