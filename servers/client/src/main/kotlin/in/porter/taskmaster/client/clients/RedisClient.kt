package `in`.porter.taskmaster.client.clients
import `in`.porter.taskmaster.client.config.ClientConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import redis.clients.jedis.Jedis
object RedisClient  {

    private  val REDIS_HOST = ClientConfig.Redis.host.also { println("Redis Host: $it") }
    private  val REDIS_PORT = ClientConfig.Redis.port ?: 6379.also { println("Redis Port: $it") }

    val jedis: Jedis by lazy {
        println("Initializing Redis Connection...")
        Jedis(REDIS_HOST, REDIS_PORT).apply {
            println("Connected to Redis at $REDIS_HOST:$REDIS_PORT")
        }
    }
}










