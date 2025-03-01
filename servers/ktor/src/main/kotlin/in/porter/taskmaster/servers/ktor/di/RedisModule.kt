package `in`.porter.taskmaster.servers.ktor.di




import dagger.Module
import dagger.Provides
import redis.clients.jedis.Jedis
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisService
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisService

import javax.inject.Singleton

@Module
class RedisModule {

    @Provides
    @Singleton
    fun provideUserRedisActions(jedis: Jedis): UserRedisActions = UserRedisService(jedis)


    @Provides
    @Singleton
    fun provideTaskRedisActions(jedis: Jedis): TaskRedisActions = TaskRedisService(jedis)
}
