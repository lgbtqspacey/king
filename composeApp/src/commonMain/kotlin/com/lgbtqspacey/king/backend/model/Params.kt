package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateItemResponse(
    val id: String
)

@Serializable
data class FilterDefault(
    val page: Int?,
    val limit: Int?,
)

@Serializable
data class FilterUser(
    val id: String?,
    val email: String?,
    val discordId: String?,
    val username: String?,
    val page: Int?,
    val limit: Int?,
)

@Serializable
data class FilterReports(
    val userId: String,
    val from: String?,
    val to: String?,
    val page: Int?,
    val limit: Int?
)
