package com.example.dto

import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val error: Boolean? = true,
    val statusCode: Int?=500,
    val message: String? = "",
    val data: T? = null
)

