package `in`.porter.taskmaster.servers.commons.di.factories



import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import `in`.porter.taskmaster.data.Entities.UserEntities.UserEntity
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {   // Used to initialize databases and setup tables
    val configLoader = ClientConfig

    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl =  configLoader.getDbUrl()// Fetch URL from config
            driverClassName = configLoader.getDbDriver()
            username =configLoader.getDbUsername()
            password = configLoader.getDbPassword()
            maximumPoolSize = 10
        }

        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(UserEntity.users)
        }
    }
}

