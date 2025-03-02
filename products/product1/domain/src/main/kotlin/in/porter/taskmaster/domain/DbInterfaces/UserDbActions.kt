package `in`.porter.taskmaster.domain.DbInterfaces

import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse


interface UserDbActions {
    suspend fun getUserFromDb(userId: Int): UserResponse?
    suspend fun addUserInDb(request: UserRequest): Boolean
    suspend fun deleteUserFromDb(userId: Int,password: String): Boolean
    suspend fun updatePasswordInDb(request: UpdatePasswordRequest): Boolean
    suspend fun isPasswordValidForUser(userId: Int, password: String): Boolean
}

