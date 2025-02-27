package `in`.porter.taskmaster.servers.ktor.di



import dagger.Module
import dagger.Provides
import redis.clients.jedis.Jedis
import `in`.porter.taskmaster.client.clients.RedisClient
import javax.inject.Singleton

@Module
object JedisModule {

    @Provides
    @Singleton
    fun provideJedis(): Jedis {
        return RedisClient.jedis
    }
}
