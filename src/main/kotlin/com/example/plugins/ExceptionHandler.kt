package com.example.plugins

import com.example.exception.InvalidParameterException
import com.example.exception.NotFoundException
import com.example.util.Extension.errorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when(cause) {
                is NotFoundException -> call.errorResponse(HttpStatusCode.NotFound,cause.message.toString())
                is InvalidParameterException -> call.errorResponse(HttpStatusCode.BadRequest,cause.message.toString())
                else-> call.errorResponse(HttpStatusCode.InternalServerError,cause.message.toString())
            }
        }
    }
}
