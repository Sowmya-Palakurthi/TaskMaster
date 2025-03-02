package `in`.porter.taskmaster.domain.RedisInterfaces

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserRedisActions{
    fun getUserData(uid: Int): UserResponse?
    fun deleteUserData(uid: Int): Boolean
    fun addUserData(request: UserRequest): Boolean
   fun setUserData(userId:Int, time:Long,userJson: String): Boolean
}
