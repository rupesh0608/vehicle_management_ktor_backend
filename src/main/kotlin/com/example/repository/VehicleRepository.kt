package com.example.repository

import com.example.enum.FuelType
import com.example.enum.VehicleBrand
import com.example.enum.VehicleCondition
import com.example.enum.VehicleType
import com.ongraph.entity.Vehicle
import com.ongraph.entity.Vehicle.default
import com.ongraph.entity.Vehicle.nullable
import com.ongraph.entity.VehicleDto
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDate

object VehicleRepository {

    fun getAllVehicles(): List<VehicleDto> {
        val vehicles = mutableListOf<VehicleDto>()
        transaction {
            Vehicle.selectAll().forEach {
                vehicles.add(
                    VehicleDto(
                        id = it[Vehicle.id],
                        vehicleCondition = it[Vehicle.vehicleCondition],
                        vehicleType = it[Vehicle.vehicleType],
                        brand = it[Vehicle.brand],
                        model = it[Vehicle.model],
                        buildYear = it[Vehicle.buildYear],
                        registrationNumber = it[Vehicle.registrationNumber],
                        fuelType = it[Vehicle.fuelType],
                        fuelTankCapacityInLtrs = it[Vehicle.fuelTankCapacityInLtrs],
                        purchaseDate = it[Vehicle.purchaseDate],
                        purchasePrice = it[Vehicle.purchasePrice],
                        kmReading = it[Vehicle.kmReading],
                    )
                )
            }
        }
        return vehicles
    }

    fun getVehicleById(id: Int): VehicleDto? {
        return transaction {
            Vehicle.select{ Vehicle.id eq id }.map {
                VehicleDto(
                    id = it[Vehicle.id],
                    vehicleCondition = it[Vehicle.vehicleCondition],
                    vehicleType = it[Vehicle.vehicleType],
                    brand = it[Vehicle.brand],
                    model = it[Vehicle.model],
                    buildYear = it[Vehicle.buildYear],
                    registrationNumber = it[Vehicle.registrationNumber],
                    fuelType = it[Vehicle.fuelType],
                    fuelTankCapacityInLtrs = it[Vehicle.fuelTankCapacityInLtrs],
                    purchaseDate = it[Vehicle.purchaseDate],
                    purchasePrice = it[Vehicle.purchasePrice],
                    kmReading = it[Vehicle.kmReading]
                )
            }.singleOrNull()
        }
    }

    fun createVehicle(
        vehicleCondition:VehicleCondition,
        vehicleType:VehicleType,
        brand:VehicleBrand,
        model:String,
        buildYear:Int,
        registrationNumber :String,
        fuelType:FuelType,
        fuelTankCapacityInLtrs :Double,
        purchaseDate:String,
        purchasePrice:Double,
        kmReading:Double,
    ): Int {
        return transaction {
            Vehicle.insert {
                     it[Vehicle.vehicleCondition] = vehicleCondition
                     it[Vehicle.vehicleType] = vehicleType
                     it[Vehicle.brand] = brand
                     it[Vehicle. model] =  model
                     it[Vehicle.buildYear] = buildYear
                     it[Vehicle.registrationNumber] = registrationNumber
                     it[Vehicle.fuelType] = fuelType
                     it[Vehicle.fuelTankCapacityInLtrs] = fuelTankCapacityInLtrs
                     it[Vehicle.purchaseDate] =purchaseDate
                     it[Vehicle.purchasePrice] =purchasePrice
                     it[Vehicle. kmReading] = kmReading
            } get Vehicle.id
        }
    }

}