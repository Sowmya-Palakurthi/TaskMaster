package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse

interface TaskRedisActions {

    fun getTaskData(userId: Int): List<TaskResponse>
    fun deleteTaskData(uid:Int,uuid:Int): Boolean
    fun addTaskData(request: TaskResponse): Boolean
}