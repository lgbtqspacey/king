package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

/**
 * Handles data from backend.
 *
 * @param isSuccess the request status
 * @param errorCode in case the response is an error
 * @param errorMessage in case the response is an error
 * @param response generic `HashMap` to handle `JSON Objects`
 * @param userDetails list of users with detailed info
 * @param usersSummarized list of users with summarized info
 */
@Serializable
data class ApiResult(
    val isSuccess: Boolean = false,
    val errorCode: Int? = null,
    val errorMessage: String = "",
    val response: HashMap<String, String>? = null,
    val userDetails: UserDetails? = null,
    val usersSummarized: UsersSummarized? = null,
)

@Serializable
data class UserDetails (
    val count: Int,
    val page: Int,
    val limit: Int,
    val data: List<User>
)

@Serializable
data class UsersSummarized(
    val count: Int,
    val page: Int,
    val limit: Int,
    val data: List<UserSummary>
)
