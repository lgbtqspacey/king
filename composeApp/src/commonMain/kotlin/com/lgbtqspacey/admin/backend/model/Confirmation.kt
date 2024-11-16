package com.lgbtqspacey.admin.backend.model

data class Confirmation(
    val sessionToken: String,
    val sessionExpiration: String,
    val sessionUserId: String,
    val sessionDeviceOS: String
)
