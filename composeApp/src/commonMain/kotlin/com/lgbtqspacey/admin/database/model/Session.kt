package com.lgbtqspacey.admin.database.model

data class Session(
    val token: String = "",
    val expiration: String = "",
    val userId: String = "",
    val name: String = "",
    val accessLevel: String = "",
    val pronouns: String = "",
)
