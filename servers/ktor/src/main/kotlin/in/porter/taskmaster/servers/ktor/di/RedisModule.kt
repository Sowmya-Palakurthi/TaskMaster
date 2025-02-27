package `in`.porter.taskmaster.servers.ktor.di




import dagger.Module
import dagger.Provides
import redis.clients.jedis.Jedis
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisService
import javax.inject.Singleton

@Module
class RedisModule {

    @Provides
    @Singleton
    fun provideTaskRedisActions(jedis: Jedis): TaskRedisActions = TaskRedisService(jedis)
}
