package com.example.util

import com.example.dto.ApiResponse
import com.example.exception.InvalidParameterException
import com.example.util.Extension.errorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

object ValidatorExtension {

     fun ApplicationCall.validateParameter(parameter:String,errorMsg:String):String {
        return this.parameters["vehicleCondition"] ?: throw InvalidParameterException(errorMsg)
     }

}