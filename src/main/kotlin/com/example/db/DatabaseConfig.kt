import com.ongraph.entity.Vehicle
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun init() {
        Database.connect(
            url = "jdbc:mysql://sql.freedb.tech:3306/freedb_vehiclemanagement",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "freedb_rupesh",
            password = "pEAB4CsqGYP?v6R"
        )
    }


    fun initTable(){
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                Vehicle
            )
        }
    }
}