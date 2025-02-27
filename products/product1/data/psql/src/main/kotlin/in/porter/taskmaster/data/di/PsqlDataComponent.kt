package `in`.porter.taskmaster.data.di
//
//import dagger.Binds
//import dagger.BindsInstance
//import dagger.Component
//import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
//import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
//import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
//import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
//import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
//import io.micrometer.core.instrument.MeterRegistry
//import org.jetbrains.exposed.sql.Database
//import javax.inject.Singleton
//
//@Singleton
//@Component(
//  modules = [
//    UtilsModule::class,
//    DataAppModule::class,
//    JedisModule::class
//  ]
//)
//interface PsqlDataComponent {
//
//  fun getUserRedisService(): UserRedisActions
//  fun getTaskRedisService(): TaskRedisActions
//  fun getUserDbService(): UserDbActions
//  fun getTaskDbService(): TaskDbActions
//  fun getTaskMapperService(): TaskMapperActions
//
//  @Component.Builder
//  interface Builder {
//    @BindsInstance
//    fun database(db: Database): Builder
//
//    @BindsInstance
//    fun meterRegistry(meterRegistry: MeterRegistry): Builder
//
//
//
//    fun build(): PsqlDataComponent
//  }
//}



import dagger.BindsInstance
import dagger.Component
import `in`.porter.taskmaster.data.DatabaseRepository.*
import `in`.porter.taskmaster.data.RedisRepository.*
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import io.micrometer.core.instrument.MeterRegistry
import org.jetbrains.exposed.sql.Database
import redis.clients.jedis.Jedis
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    UtilsModule::class,
    DataAppModule::class,
    JedisModule::class  // Ensure Jedis is correctly injected
  ]
)
interface PsqlDataComponent {

  fun getUserRedisService(): UserRedisActions
  fun getTaskRedisService(): TaskRedisActions
  fun getUserDbService(): UserDbActions
  fun getTaskDbService(): TaskDbActions
  fun getTaskMapperService(): TaskMapperActions

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun database(db: Database): Builder

    @BindsInstance
    fun meterRegistry(meterRegistry: MeterRegistry): Builder

    @BindsInstance
    fun jedis(jedis: Jedis): Builder

    fun build(): PsqlDataComponent
}}


//
//@Component.Builder
//interface Builder {
//
//  @BindsInstance
//  fun database(db: Database): Builder
//
//  @BindsInstance
//  fun meterRegistry(meterRegistry: MeterRegistry): Builder
//
//  fun build(): PsqlDataComponent  // ✅ Make sure this is the only method returning the component
//}
