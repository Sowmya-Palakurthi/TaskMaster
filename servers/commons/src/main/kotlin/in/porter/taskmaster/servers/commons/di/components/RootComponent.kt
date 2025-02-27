//package `in`.porter.taskmaster.servers.commons.di.components
//
//import `in`.porter.kotlinutils.commons.config.Environment
//import `in`.porter.kotlinutils.sqs.coroutines.client.SQSClient
//import `in`.porter.taskmaster.servers.commons.di.modules.MicrometerModule
//import `in`.porter.taskmaster.servers.commons.di.modules.PsqlModule
//import `in`.porter.taskmaster.servers.commons.di.modules.SQSClientModule
//import `in`.porter.taskmaster.servers.commons.di.modules.UtilsModule
//import com.zaxxer.hikari.HikariDataSource
//import dagger.Component
//import io.ktor.client.*
//import io.micrometer.core.instrument.MeterRegistry
//import org.jetbrains.exposed.sql.Database
//import javax.inject.Singleton
//
//@Singleton
//@Component(
//  modules = [
//    PsqlModule::class,
//    SQSClientModule::class,
//    MicrometerModule::class,
//    UtilsModule::class,
//
//  ]
//)
//interface RootComponent {
//  val database: Database
//  val hikariDataSource: HikariDataSource
//  val meterRegistry: MeterRegistry
//  val sqsClient: SQSClient
//  val httpClient: HttpClient
//  val environment: Environment
//
//
//
//
//}
//
//
//
//


package `in`.porter.taskmaster.servers.commons.di.components


//
import `in`.porter.kotlinutils.commons.config.Environment
import `in`.porter.kotlinutils.sqs.coroutines.client.SQSClient
import `in`.porter.taskmaster.servers.commons.di.modules.*
import `in`.porter.taskmaster.servers.commons.di.modules.MicrometerModule
import `in`.porter.taskmaster.servers.commons.di.modules.PsqlModule
import `in`.porter.taskmaster.servers.commons.di.modules.SQSClientModule
import `in`.porter.taskmaster.servers.commons.di.modules.UtilsModule
import com.zaxxer.hikari.HikariDataSource
import dagger.Component
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.TaskRepositoryActions
import `in`.porter.taskmaster.domain.UserRepositoryActions
import io.ktor.client.*
import io.micrometer.core.instrument.MeterRegistry
import org.jetbrains.exposed.sql.Database
import redis.clients.jedis.Jedis
import javax.inject.Singleton
//
//@Singleton
//@Component(
//  modules = [
//    PsqlModule::class,
//    SQSClientModule::class,
//    MicrometerModule::class,
//    UtilsModule::class, // Ensure UtilsModule provides `Jedis`
//    JedisModule::class  // Ensure Jedis is properly injected
//  ]
//)
//interface RootComponent {
//  val database: Database
//  val hikariDataSource: HikariDataSource
//  val meterRegistry: MeterRegistry
//  val sqsClient: SQSClient
//  val httpClient: HttpClient
//  val environment: Environment
//  val jedis: Jedis // ✅ Provided via `JedisModule`
//  val userDbService: UserDbActions
//  val taskDbService: TaskDbActions
//  val userRedisService: UserRedisActions
//  val taskRedisService: TaskRedisActions
//  val userRepository: UserRepositoryActions
//  val taskRepository: TaskRepositoryActions
//  val taskMapper: TaskMapperActions
//
//  companion object {
//    val instance: RootComponent by lazy {
//      DaggerRootComponent.create()
//    }
//  }
//}
@Singleton
@Component(
  modules = [

    PsqlModule::class,
    SQSClientModule::class,
    MicrometerModule::class,
    UtilsModule::class,
  JedisModule::class
  ]
)
interface RootComponent {
  val database: Database
  val hikariDataSource: HikariDataSource
  val meterRegistry: MeterRegistry
  val sqsClient: SQSClient
  val httpClient: HttpClient
  val environment: Environment
//  val jedis: Jedis
  val userDbService: UserDbActions
  val taskDbService: TaskDbActions
  val userRedisService: UserRedisActions
  val taskRedisService: TaskRedisActions
  val userRepository: UserRepositoryActions
  val taskRepository: TaskRepositoryActions
  val taskMapper: TaskMapperActions
  fun getJedis(): Jedis

  @Component.Builder
  interface Builder {
    fun build(): RootComponent  // 👈 Add this to allow Dagger to construct the component
  }

//  companion object {
//    val instance: RootComponent by lazy {
//      DaggerRootComponent.builder().build()
//    }
//  }
//}

companion object {
  val instance: RootComponent by lazy {
    DaggerRootComponent.create()
  }
}}
