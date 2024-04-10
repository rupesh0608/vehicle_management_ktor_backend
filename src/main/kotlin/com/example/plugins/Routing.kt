package com.example.plugins

import com.example.routes.vehicle.vehicleRoute
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing() {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
       vehicleRoute()
    }
}
