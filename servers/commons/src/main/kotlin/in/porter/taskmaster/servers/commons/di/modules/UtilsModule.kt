package `in`.porter.taskmaster.servers.commons.di.modules


import com.fasterxml.jackson.databind.PropertyNamingStrategies
import `in`.porter.kotlinutils.commons.config.Environment
import `in`.porter.kotlinutils.serde.jackson.custom.*
import `in`.porter.kotlinutils.webclient.ktor.features.*
import `in`.porter.taskmaster.data.DatabaseRepository.*
import `in`.porter.taskmaster.data.RedisRepository.*
import `in`.porter.taskmaster.data.CommonRepository.TaskRepositoryImplementation
import `in`.porter.taskmaster.data.CommonRepository.UserRepositoryImplementation
import `in`.porter.taskmaster.data.MapperClasses.*
import dagger.Module
import dagger.Provides
import `in`.porter.taskmaster.client.clients.RedisClient.jedis
import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
import `in`.porter.taskmaster.domain.MapperInterfaces.UserMapperActions
import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
import `in`.porter.taskmaster.servers.commons.extensions.loadResource
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import redis.clients.jedis.Jedis
import java.util.*
import javax.inject.Singleton

@Module
class UtilsModule {

  @Provides
  @Singleton
  fun provideHttpClient(): HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
      jackson {
        propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        registerModules(
          EitherSerde.module,
          InstantSerde.millisModule,
          UrlSerde.urlModule,
          MoneySerde.moneyModule
        )
      }
    }
    install(CountryClientFeature)
    install(LanguageClientFeature)
  }

  @Provides
  @Singleton
  fun provideEnvironment(): Environment =
    Properties().loadResource(this::javaClass, "application.properties")
      .getProperty("env")
      .let { Environment.valueOf(it) }

  @Provides
  @Singleton
  fun provideUserDbActions(): UserDbActions = UserDbImplementation()

  @Provides
  @Singleton
  fun provideTaskDbActions(): TaskDbActions = TaskDbImplementation(provideTaskMapper())

  @Provides
  @Singleton
  fun provideUserRedisActions(jedis: Jedis): UserRedisActions = UserRedisService(jedis)

  @Provides
  @Singleton
  fun provideTaskRedisActions(jedis: Jedis): TaskRedisActions = TaskRedisService(jedis)

  @Provides
  @Singleton
  fun provideUserRepository(): UserRepositoryActions = UserRepositoryImplementation(
    provideUserRedisActions(jedis),
    provideUserDbActions()
  )

  @Provides
  @Singleton
  fun provideUserMapper(): UserMapperActions = UserMapperService()

  @Provides
  @Singleton
  fun provideTaskRepository(): TaskRepositoryActions = TaskRepositoryImplementation(
    provideTaskRedisActions(jedis),
    provideTaskDbActions(),
    provideUserDbActions(),
    provideTaskMapper()
  )

  @Provides
  @Singleton
  fun provideTaskMapper(): TaskMapperActions = TaskMapperService()

//  @Provides
//  fun provideRequestMapper(): RequestMapper {
//    return RequestMapper()
//  }
}
//
//import `in`.porter.taskmaster.api.service.RequestMapper
//import com.fasterxml.jackson.databind.PropertyNamingStrategies
//import `in`.porter.kotlinutils.commons.config.Environment
//import `in`.porter.kotlinutils.serde.jackson.custom.*
//import `in`.porter.kotlinutils.webclient.ktor.features.*
//import `in`.porter.taskmaster.data.DatabaseRepository.*
//import `in`.porter.taskmaster.data.RedisRepository.*
//import `in`.porter.taskmaster.data.CommonRepository.TaskRepositoryImplementation
//import `in`.porter.taskmaster.data.CommonRepository.UserRepositoryImplementation
//import `in`.porter.taskmaster.data.MapperClasses.*
//import dagger.Module
//import dagger.Provides
//import `in`.porter.taskmaster.client.clients.RedisClient.jedis
//import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
//import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
//import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
//import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
//import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
//import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
//import `in`.porter.taskmaster.domain.MapperInterfaces.UserMapperActions
//import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
//import `in`.porter.taskmaster.servers.commons.extensions.loadResource
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.serialization.jackson.*
//import redis.clients.jedis.Jedis
//import java.util.*
//import javax.inject.Singleton
//
//@Module
//class UtilsModule {
//
//  @Provides
//  @Singleton
//  fun provideHttpClient(): HttpClient = HttpClient(CIO) {
//    install(ContentNegotiation) {
//      jackson {
//        propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
//        registerModules(
//          EitherSerde.module,
//          InstantSerde.millisModule,
//          UrlSerde.urlModule,
//          MoneySerde.moneyModule
//        )
//      }
//    }
//    install(CountryClientFeature)
//    install(LanguageClientFeature)
//  }
//
//  @Provides
//  @Singleton
//  fun provideEnvironment(): Environment =
//    Properties().loadResource(this::javaClass, "application.properties")
//      .getProperty("env")
//      .let { Environment.valueOf(it) }
//
//
//    @Provides
//    @Singleton
//    fun provideUserDbActions(): UserDbActions = UserDbImplementation()
//
//    @Provides
//    @Singleton
//    fun provideTaskDbActions(): TaskDbActions = TaskDbImplementation(provideTaskMapper())
//
//    @Provides
//    @Singleton
//    fun provideUserRedisActions(jedis: Jedis): UserRedisActions = UserRedisService(jedis)
//
//    @Provides
//    @Singleton
//    fun provideTaskRedisActions(jedis: Jedis): TaskRedisActions = TaskRedisService(jedis)
//
//    @Provides
//    @Singleton
//    fun provideUserRepository(): UserRepositoryActions = UserRepositoryImplementation(
//      provideUserRedisActions(jedis),
//      provideUserDbActions()
//    )
//
//
//  @Provides
//  @Singleton
//  fun provideUserMapper(): UserMapperActions = UserMapperService()
//
//
//    @Provides
//    @Singleton
//    fun provideTaskRepository(): TaskRepositoryActions = TaskRepositoryImplementation(
//      provideTaskRedisActions(jedis),
//      provideTaskDbActions(),
//      provideUserDbActions(),
//      provideTaskMapper()
//    )
//
//   @Provides
//    @Singleton
//    fun provideTaskMapper(): TaskMapperActions = TaskMapperService()
//
//  @Provides
//  fun provideRequestMapper(): RequestMapper {
//    return RequestMapper()
//  }
//}