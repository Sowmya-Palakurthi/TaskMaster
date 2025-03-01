package `in`.porter.taskmaster.data.RedisRepository

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserRedisActions{
    fun getUserData(uid: Int): UserResponse?
    fun deleteUserData(uid: Int): Boolean
    fun addUserData(request: UserRequest): Boolean
    fun setUserData(data: String, time: Long, uid: Int): Boolean
}
