package `in`.porter.taskmaster.data.di

import dagger.BindsInstance
import dagger.Component
import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
import io.micrometer.core.instrument.MeterRegistry
import org.jetbrains.exposed.sql.Database
import redis.clients.jedis.Jedis
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    UtilsModule::class,
    DataAppModule::class,
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

