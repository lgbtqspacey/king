package com.lgbtqspacey.admin.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val userId: String,
    val type: String,
    val isFirstOccurrence: Boolean,
    val severity: String,
    val description: String,
    val date: String,
    val time: String,
    val place: String,
    val additionalInfo: String,
    val followUp: String,
    val createdBy: String,
    val witnesses: List<Details?>,
    val peopleInvolved: List<Details?>,
)

@Serializable
data class Details(
    val name: String,
    val contactInfo: String,
    val relation: String,
    val report: String,
)
