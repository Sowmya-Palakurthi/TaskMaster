package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse

interface UserRedisActions{


    fun getUserData(uid: Int): UserResponse?
    fun deleteUserData(userId: Int): Boolean
    fun addUserData(request: UserRequest): Boolean
    fun setUserData(data: String, time: Long, userId: Int): Boolean

}
