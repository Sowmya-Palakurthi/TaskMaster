package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import redis.clients.jedis.Jedis
import javax.inject.Inject

class UserRedisService @Inject constructor(private val jedis: Jedis) : UserRedisActions {

    private val logger: Logger = LogManager.getLogger(UserRedisService::class.java)

    override fun getUserData(uid: Int): UserResponse? {
        return try {
            val data = jedis.get("user:$uid")
            logger.info("Fetched from Redis: $data") // Debug log
            data?.let { Json.decodeFromString<UserResponse>(it) }
        } catch (e: Exception) {
            logger.error("Error fetching user data from cache for ID $uid: ${e.localizedMessage}", e)
            null
        }
    }


    override fun deleteUserData(uid: Int): Boolean {
        return try {
            val taskIds = jedis.smembers("tasks:set:$uid")
            taskIds.forEach { jedis.del("task:$uid:$it") }
            jedis.del("tasks:set:$uid")
            jedis.del("user:$uid")
            true
        } catch (e: Exception) {
            logger.error("Error deleting user data from cache for ID $uid: ${e.localizedMessage}", e)
            false
        }
    }

    override fun addUserData(request: UserRequest): Boolean {
        return try {
            val userJson = Json.encodeToString(request)
            jedis.set("user:${request.uid}", userJson)

            true
        } catch (e: Exception) {
            logger.error("Error adding user data to cache for ID ${request.uid}: ${e.localizedMessage}", e)
            false
        }
    }

    override fun setUserData(userId:Int, time:Long,userJson: String): Boolean {
        return try {
            jedis.set("user:$userId", userJson)
            true
        } catch (exception: Exception) {
            logger.error("Error setting user data in cache for ID $userId: ${exception.localizedMessage}", exception)
            false
        }
    }
}