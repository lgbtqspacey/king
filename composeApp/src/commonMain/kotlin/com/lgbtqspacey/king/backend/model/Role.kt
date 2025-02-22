package com.lgbtqspacey.king.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val name: String,
    val description: String,
    val function: List<String>,
)
