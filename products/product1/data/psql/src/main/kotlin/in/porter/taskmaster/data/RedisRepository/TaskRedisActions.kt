package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest


interface TaskRedisActions{
    fun getTaskData(uid: Int): List<TaskRequest>?
    fun deleteTaskData(uid: Int, uuid: Int): Boolean
    fun addTaskData(request: TaskRequest): Boolean
}
