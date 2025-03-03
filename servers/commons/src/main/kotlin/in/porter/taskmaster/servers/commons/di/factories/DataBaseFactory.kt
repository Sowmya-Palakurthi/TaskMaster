package `in`.porter.taskmaster.servers.commons.di.factories
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskEntity
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init()
    {
        Database.connect(hikari())

        transaction{
            SchemaUtils.create(UserEntity.users)
            SchemaUtils.create(TaskEntity.tasks)
        }
    }

    fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = System.getenv("DATABASE_URL")
        config.driverClassName = "org.postgresql.Driver"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO){
            transaction { block() }
        }

}