package `in`.porter.taskmaster.domain.Repository

import `in`.porter.taskmaster.data.Entities.UpdatePasswordRequest
import `in`.porter.taskmaster.data.Entities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserResponse


interface UserActions {
    suspend fun getUser(userId: Int): UserResponse?
    suspend fun addUser(request: UserRequest):Boolean
    suspend fun deleteUser(userId: Int, password: String): Boolean
    suspend fun updateUser(request: UpdatePasswordRequest): Boolean
}
