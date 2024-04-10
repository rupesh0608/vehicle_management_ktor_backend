package com.example.routes.vehicle

import com.example.dto.ApiResponse
import com.example.enum.FuelType
import com.example.enum.VehicleBrand
import com.example.enum.VehicleCondition
import com.example.enum.VehicleType
import com.example.exception.NotFoundException
import com.example.repository.VehicleRepository
import com.example.util.Extension.errorResponse
import com.example.util.Extension.successResponse
import com.example.util.ValidatorExtension.validateParameter
import com.ongraph.entity.Vehicle
import com.ongraph.entity.VehicleDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.vehicleRoute() {
    route("/api") {

        get("/vehicles") {
            call.successResponse(HttpStatusCode.OK, "Available vehicle List", data = VehicleRepository.getAllVehicles())
        }

        post("/vehicle") {
            val vehicleCondition: String = call.validateParameter(
                "vehicleCondition",
                "Missing 'vehicleCondition' parameter expected values[NEW,OLD]"
            )
            val vehicleType =
                call.validateParameter("vehicleType", "Missing 'vehicleType' parameter expected values[BIKE,CAR,TRUCK]")
            val brand = call.validateParameter(
                "brand",
                "Missing 'brand' parameter expected values[HERO,HONDA,HERO_HONDA,....]",
            )
            val model = call.validateParameter(
                "model",
                "Missing 'model' parameter",
            )
            val buildYear = call.validateParameter(
                "buildYear",
                "Missing 'buildYear' parameter"
            )
            val registrationNumber = call.validateParameter(
                "registrationNumber",
                "Missing 'registrationNumber' parameter"
            )
            val fuelType = call.validateParameter(
                "fuelType",
                "Missing 'fuelType' parameter"
            )
            val fuelTankCapacityInLtrs = call.validateParameter(
                "fuelTankCapacityInLtrs",
                "Missing 'fuelTankCapacityInLtrs' parameter"
            )
            val purchaseDate = call.validateParameter(
                "purchaseDate",
                "Missing 'purchaseDate' parameter"
            )
            val purchasePrice = call.validateParameter(
                "purchasePrice",
                "Missing 'purchasePrice' parameter"
            )
            val kmReading = call.validateParameter(
                "kmReading",
                "Missing 'kmReading' parameter"
            )

            val vehicleId = VehicleRepository.createVehicle(
                vehicleCondition = VehicleCondition.valueOf(vehicleCondition.uppercase()),
                vehicleType = VehicleType.valueOf(vehicleType.uppercase()),
                brand = VehicleBrand.valueOf(brand.uppercase()),
                model = model,
                buildYear = buildYear.toInt(),
                registrationNumber = registrationNumber,
                fuelType = FuelType.valueOf(fuelType.uppercase()),
                fuelTankCapacityInLtrs = fuelTankCapacityInLtrs.toDouble(),
                purchaseDate = purchaseDate,
                purchasePrice = purchasePrice.toDouble(),
                kmReading = kmReading.toDouble(),
            )
            call.successResponse(
                HttpStatusCode.OK,
                message = "Vehicle created with ID: $vehicleId",
                data = VehicleRepository.getVehicleById(vehicleId)
            )
        }

        get("/vehicle/{id}") {
            val vehicleId = call.parameters["id"]?.toIntOrNull()
            if (vehicleId != null) {
                val vehicle = VehicleRepository.getVehicleById(vehicleId)
                if (vehicle != null) {
                    call.successResponse(
                        HttpStatusCode.OK, "Vehicle detail for vehicleId:$vehicleId",
                        data = vehicle
                    )
                } else throw NotFoundException("Vehicle Not Found for id:$vehicleId.")
            } else {
                call.errorResponse(HttpStatusCode.BadRequest, "Invalid vehicle ID")
            }
        }

    }
}