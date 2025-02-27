package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import javax.inject.Inject


class UserRedisService @Inject constructor( private val jedis:Jedis) : UserRedisActions {



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
            println("Error deleting  user data from cache for ID $uid: ${e.localizedMessage}")
            false
        }
    }

    override fun addUserData(request: UserRequest): Boolean {
        return try {
            val userJson = Json.encodeToString(request)
            jedis.set("user:${request.uid}", userJson)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun getTaskData(uid: Int): List<TaskRequest>? {
        return try {
            val taskIds = jedis.smembers("tasks:set:$uid")
            taskIds.mapNotNull { uuid ->
                jedis.get("task:$uid:$uuid")?.let { Json.decodeFromString<TaskRequest>(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun deleteTaskData(uid: Int, uuid: Int): Boolean {
        return try {
            jedis.del("task:$uid:$uuid")
            jedis.srem("tasks:set:$uid", uuid.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun addTaskData(request: TaskRequest): Boolean {
        return try {
            val taskJson = Json.encodeToString(request)
            val uid = request.uid
            val uuid = request.uuid
            val keyType = jedis.type("tasks:set:$uid")
            if (keyType != "set" && keyType != "none") {
                jedis.del("tasks:set:$uid")
            }
            jedis.set("task:$uid:$uuid", taskJson)
            jedis.sadd("tasks:set:$uid", uuid.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun setUserData(data: String, time: Long, userId: Int): Boolean {
        return try {
            jedis.setex("user:$userId", time, data)
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }
}
