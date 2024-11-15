package com.lgbtqspacey.admin.backend.model

data class Confirmation(
    val token: String,
    val expiration: String,
    val userId: String,
    val deviceOs: String,
    val deviceIp: String,
    val deviceLocation: String,
)
