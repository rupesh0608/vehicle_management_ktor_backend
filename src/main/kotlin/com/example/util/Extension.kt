package com.example.util

import com.example.dto.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

object Extension {

    suspend fun ApplicationCall.errorResponse(statusCode:HttpStatusCode,message:String) {
        return this.respond(
            status = statusCode, message = ApiResponse(
                error = true,
                statusCode = statusCode.value,
                message = message,
                data = null
            )
        )
    }

    suspend inline fun <reified T> ApplicationCall.successResponse(statusCode:HttpStatusCode, message:String, data:T) {
        return this.respond(
            statusCode, ApiResponse(
                error = false,
                statusCode = statusCode.value,
                message = message,
                data =data
            )
        )
    }
}