package com.ongraph.entity

import com.example.enum.FuelType
import com.example.enum.VehicleBrand
import com.example.enum.VehicleCondition
import com.example.enum.VehicleType
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.time.LocalDate

object Vehicle : Table() {

    val id: Column<Int> = integer("id").autoIncrement()
    val vehicleCondition = enumerationByName("vehicleCondition", 100, VehicleCondition::class).nullable()
    val vehicleType = enumerationByName("vehicleType", 100, VehicleType::class).nullable()
    val brand = enumerationByName("brand", 100, VehicleBrand::class).nullable()
    val model = varchar("model", 100).nullable()
    val buildYear = integer("buildYear").default(LocalDate.now().year)
    val registrationNumber = varchar("registrationNumber", 100).nullable()
    val fuelType = enumerationByName("fuelType", 100, FuelType::class).nullable()
    val fuelTankCapacityInLtrs = double("fuelTankCapacityInLtrs").default(0.00)
    val purchaseDate = varchar("purchaseDate", 100).nullable()
    val purchasePrice = double("purchasePrice").default(0.00)
    val kmReading = double("kmReading").default(0.00)

    override val primaryKey = PrimaryKey(id)
}