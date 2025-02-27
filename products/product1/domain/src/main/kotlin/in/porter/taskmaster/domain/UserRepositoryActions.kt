package `in`.porter.taskmaster.domain

import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserRepositoryActions {
    suspend fun getUser(userId: Int): UserResponse?
    suspend fun addUser(request: UserRequest):Boolean
    suspend fun deleteUser(userId: Int, password: String): Boolean
    suspend fun updateUser(request: UpdatePasswordRequest): Boolean
}