
package `in`.porter.taskmaster.servers.commons.di.components


import `in`.porter.kotlinutils.commons.config.Environment
import `in`.porter.kotlinutils.sqs.coroutines.client.SQSClient
import `in`.porter.taskmaster.servers.commons.di.modules.*
import `in`.porter.taskmaster.servers.commons.di.modules.MicrometerModule
import `in`.porter.taskmaster.servers.commons.di.modules.PsqlModule
import `in`.porter.taskmaster.servers.commons.di.modules.SQSClientModule
import `in`.porter.taskmaster.servers.commons.di.modules.UtilsModule
import com.zaxxer.hikari.HikariDataSource
import dagger.Component
import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import io.ktor.client.*
import io.micrometer.core.instrument.MeterRegistry
import org.jetbrains.exposed.sql.Database
import redis.clients.jedis.Jedis
import javax.inject.Singleton

@Singleton
@Component(
  modules = [

    PsqlModule::class,
    SQSClientModule::class,
    MicrometerModule::class,
    UtilsModule::class,
    JedisModule::class,

  ]
)
interface RootComponent {
  val database: Database
  val hikariDataSource: HikariDataSource
  val meterRegistry: MeterRegistry
  val sqsClient: SQSClient
  val httpClient: HttpClient
  val environment: Environment
  val jedis: Jedis
  val userDbService: UserDbActions
  val taskDbService: TaskDbActions
  val userRedisService: UserRedisActions
  val taskRedisService: TaskRedisActions
  val userRepository: UserRepositoryActions
  val taskRepository: TaskRepositoryActions

  @Component.Builder
  interface Builder {
    fun build(): RootComponent
  }
}

