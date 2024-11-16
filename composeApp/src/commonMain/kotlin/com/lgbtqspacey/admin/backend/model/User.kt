package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String?,
    val username: String?,
    val email: String?,
    val discordId: String?,
    val roles: List<String>?,
    val dateOfBirth: String?,
    val pronouns: String?,
    val phone: String?,
    val joinedAt: String?,
    val createdBy: String?,
    val leftAt: String?,
    val password: String?,
    val isAdmin: Boolean?,
)

