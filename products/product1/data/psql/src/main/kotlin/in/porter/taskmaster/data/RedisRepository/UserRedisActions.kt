package `in`.porter.taskmaster.data.RedisRepository



import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserRedisActions{

    fun getUserData(uid: Int): UserResponse?
    fun deleteUserData(uid: Int): Boolean
    fun addUserData(request: UserRequest): Boolean
    fun getTaskData(uid: Int): List<TaskRequest>?
    fun deleteTaskData(uid: Int, uuid: Int): Boolean
    fun addTaskData(request: TaskRequest): Boolean
    fun setUserData(data: String, time: Long, userId: Int): Boolean
}
