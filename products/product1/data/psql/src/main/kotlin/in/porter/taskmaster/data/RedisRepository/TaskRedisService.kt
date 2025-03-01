package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskEntity.tasks.uuid
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity.users.uid
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import redis.clients.jedis.Jedis


class TaskRedisService @Inject constructor(private val jedis: Jedis): TaskRedisActions {


    override fun getTaskData(userId: Int): List<TaskResponse> {
        return try {
            val taskIds = jedis.smembers("tasks:set:$uid")
            taskIds.mapNotNull {
                jedis.get("task:$uid:$uuid")?.let { Json.decodeFromString<TaskResponse>(it) }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun deleteTaskData(uid:Int, uuid:Int): Boolean {
        return try {

            jedis.del("task:$uid:$uuid")
            jedis.srem("tasks:set:$uid", uuid.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun addTaskData(request: TaskResponse): Boolean {
        return try {
            val taskJson = Json.encodeToString(request)
            val userId = request.uid
            val taskId = request.uuid
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



}

