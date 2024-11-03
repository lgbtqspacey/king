package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable

/**
 * @param password user password
 * @param identifier email or username
 */
@Serializable
data class Login(
    val password: String,
    val identifier: String,
)
