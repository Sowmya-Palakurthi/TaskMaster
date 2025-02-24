package `in`.porter.taskmaster.servers.ktor


import com.typesafe.config.ConfigFactory
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.server.config.*
import java.io.File
import javax.inject.Inject

class ConfigService @Inject constructor() :ConfigActions{
    private val config: ApplicationConfig

    init {
        val configFile = File("src/main/resources/application.conf")
        config = HoconApplicationConfig(ConfigFactory.parseFile(configFile))
    }
    override fun getDbUrl():String=config.property("ktor.database.url").getString()
    override fun getDbDriver(): String = config.property("ktor.database.driver").getString()
    override fun getDbUsername(): String = config.property("ktor.database.user").getString()
    override fun getDbPassword(): String = config.property("ktor.database.password").getString()
    override fun getRedisHost():String=config.property("ktor.redis.host").getString()
    override fun getRedisPort():Int=config.property("ktor.redis.port").getString().toInt()
    override fun getServerHost():String=config.property("ktor.server.host").getString()
    override fun getServerPort():Int=config.property("ktor.server.port").getString().toInt()
    override fun getJwtAudience():String=config.property("ktor.jwt.audience").getString()
    override fun getJwtDomain(): String = config.property("ktor.jwt.domain").getString()
    override fun getJwtRealm(): String = config.property("ktor.jwt.realm").getString()
    override fun getJwtSecret(): String = config.property("ktor.jwt.secret").getString()


}
