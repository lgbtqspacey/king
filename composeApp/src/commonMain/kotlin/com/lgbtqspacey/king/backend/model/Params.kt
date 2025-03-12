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

/**
 * Query params to filter an user.
 * Use one or more.
 */
@Serializable
data class FilterUser(
    val id: String? = null,
    val email: String? = null,
    val discordId: String? = null,
    val username: String? = null,
    val page: Int? = null,
    val limit: Int? = null,
)

/**
 * Query params to filter reports.
 *
 * @property userId required
 * @property from optional when `to` is null, otherwise is **required**.
 * @property to optional when `from` is null, otherwise is **required**.
 * @property page optional
 * @property limit optional
 */
@Serializable
data class FilterReports(
    val userId: String,
    val from: String?,
    val to: String?,
    val page: Int?,
    val limit: Int?
)
