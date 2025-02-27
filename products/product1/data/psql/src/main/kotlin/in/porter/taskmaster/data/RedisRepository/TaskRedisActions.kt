package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse

interface TaskRedisActions {

    fun getTaskData(userId: Int): List<TaskResponse>
    fun deleteTaskData(userId: Int, taskId: Int): Boolean
    fun addTaskData(request: TaskResponse): Boolean
    fun setUserData(data: String, time: Long, userId: Int): Boolean
}