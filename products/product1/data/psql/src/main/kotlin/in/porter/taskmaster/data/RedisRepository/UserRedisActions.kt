package `in`.porter.taskmaster.data.RedisRepository



import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse

interface UserRedisActions{
    fun getUserData(userId: Int): UserResponse?
    fun deleteUserData(userId: Int): Boolean
    fun addUserData(request: UserRequest): Boolean
    fun getTaskData(userId: Int): List<TaskRequest>?
    fun deleteTaskData(userId: Int, taskId: Int): Boolean
    fun addTaskData(request: TaskRequest): Boolean
    fun setUserData(data: String, time: Long, userId: Int): Boolean
}
