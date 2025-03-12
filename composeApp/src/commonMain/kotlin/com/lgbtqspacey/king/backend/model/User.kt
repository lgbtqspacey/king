package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_id") val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val discordId: String? = null,
    val teams: List<Team>? = null,
    val dateOfBirth: String? = null,
    val pronouns: String? = null,
    val phone: String? = null,
    val joinedAt: String? = null,
    val createdBy: String? = null,
    val leftAt: String? = null,
    val password: String? = null,
    val accessLevel: String? = null,
)

@Serializable
data class Team(
    val name: String,
    val role: String
)

@Serializable
data class UserSummary(
    @SerialName("_id") val id: String? = null,
    val name: String? = null,
    val pronouns: String? = null,
    val accessLevel: String? = null,
)
