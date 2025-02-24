package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import redis.clients.jedis.Jedis
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TaskRedisService @Inject constructor(private val jedis: Jedis) : TaskRedisActions {

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
            jedis.srem("tasks:set:$uid", uuid.toString()) // ✅ Convert Int to String
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun addTaskData(request: TaskRequest): Boolean {
        return try {
            val taskJson = Json.encodeToString(request)
            val uid = request.getUserId()
            val uuid = request.getTaskId()

            val keyType = jedis.type("tasks:set:$uid")
            if (keyType != "set" && keyType != "none") {
                jedis.del("tasks:set:$uid")
            }

            jedis.set("task:$uid:$uuid", taskJson)
            jedis.sadd("tasks:set:$uid", uuid.toString()) // ✅ Convert Int to String
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
