package com.ongraph.entity

import com.example.enum.FuelType
import com.example.enum.VehicleBrand
import com.example.enum.VehicleCondition
import com.example.enum.VehicleType
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.time.LocalDate

@Serializable
data class VehicleDto(
    val id: Int,
    val vehicleCondition: VehicleCondition?,
    val vehicleType: VehicleType?,
    val brand: VehicleBrand?,
    val model: String?,
    val buildYear: Int = LocalDate.now().year,
    val registrationNumber: String?,
    val fuelType: FuelType?,
    val fuelTankCapacityInLtrs: Double = 0.0,
    val purchaseDate: String?,
    val purchasePrice: Double = 0.0,
    val kmReading: Double = 0.0
)
