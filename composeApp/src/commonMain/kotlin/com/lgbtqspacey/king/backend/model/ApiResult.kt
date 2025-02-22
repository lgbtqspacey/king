package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
    val isSuccess: Boolean = false,
    val errorCode: Int? = null,
    val errorMessage: String = "",
    val data: HashMap<String, String>? = null,
)
