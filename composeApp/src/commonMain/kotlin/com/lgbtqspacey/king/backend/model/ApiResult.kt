package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
    val isSuccess: Boolean = false,
    val errorCode: Int? = null,
    val errorMessage: String = "",
    val response: HashMap<String, String>? = null,
    val responseDetails: ResponseDetails? = null
)

/**
 * @property count items in the array
 * @property page current page
 * @property limit max items on this page
 * @property data list of the requested data
 */
@Serializable
data class ResponseDetails(
    val count: Int,
    val page: Int,
    val limit: Int,
    val data: List<HashMap<String, String>>
)
