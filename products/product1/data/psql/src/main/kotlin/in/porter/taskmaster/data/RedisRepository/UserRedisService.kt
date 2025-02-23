package `in`.porter.taskmaster.data.RedisRepository


import `in`.porter.taskmaster.client.clients.RedisClient.jedis
import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class UserRedisService @Inject constructor() : UserRedisActions {

    override fun getUserData(userId: Int): UserResponse? {
        return try {
            jedis.get("user:$userId")?.let { Json.decodeFromString<UserResponse>(it) }
        } catch (e: Exception) {
            println("Error fetching user data from cache for ID $userId: ${e.localizedMessage}")
            null
        }
    }

    override fun deleteUserData(userId: Int): Boolean {
        return try {
            val taskIds = jedis.smembers("tasks:set:$userId")
            taskIds.forEach { jedis.del("task:$userId:$it") }
            jedis.del("tasks:set:$userId")
            jedis.del("user:$userId")
            true
        } catch (e: Exception) {
            println("Error deleting  user data from cache for ID $userId: ${e.localizedMessage}")
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

    override fun getTaskData(userId: Int): List<TaskRequest>? {
        return try {
            val taskIds = jedis.smembers("tasks:set:$userId")
            taskIds.mapNotNull { taskId ->
                jedis.get("task:$userId:$taskId")?.let { Json.decodeFromString<TaskRequest>(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun deleteTaskData(userId: Int, taskId: Int): Boolean {
        return try {
            jedis.del("task:$userId:$taskId")
            jedis.srem("tasks:set:$userId", taskId.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun addTaskData(request: TaskRequest): Boolean {
        return try {
            val taskJson = Json.encodeToString(request)
            val userId = request.getUserId()
            val taskId = request.getTaskId()
            val keyType = jedis.type("tasks:set:$userId")
            if (keyType != "set" && keyType != "none") {
                jedis.del("tasks:set:$userId")
            }
            jedis.set("task:$userId:$taskId", taskJson)
            jedis.sadd("tasks:set:$userId", taskId.toString())
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
