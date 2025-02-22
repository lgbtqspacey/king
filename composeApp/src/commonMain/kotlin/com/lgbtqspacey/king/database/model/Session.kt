package com.lgbtqspacey.king.database.model

data class Session(
    val token: String = "",
    val expiration: String = "",
    val userId: String = "",
)
