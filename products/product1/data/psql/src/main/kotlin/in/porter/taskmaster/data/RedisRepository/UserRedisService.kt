package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import javax.inject.Inject

class UserRedisService @Inject constructor(private val jedis: Jedis) : UserRedisActions {

    override fun getUserData(uid: Int): UserResponse? {
        return try {
            jedis.get("user:$uid")?.let { Json.decodeFromString<UserResponse>(it) }
        } catch (e: Exception) {
            println("Error fetching user data from cache for ID $uid: ${e.localizedMessage}")
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
            println("Error deleting user data from cache for ID $uid: ${e.localizedMessage}")
            false
        }
    }

    override fun addUserData(request: UserRequest): Boolean {
        return try {
            val userJson = Json.encodeToString(request)
            jedis.set("user:${request.getUserId()}", userJson)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
