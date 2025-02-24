package `in`.porter.taskmaster.domain.exceptions

import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserResponse


interface UserActions {
    suspend fun getUser(uid: Int): UserResponse?
    suspend fun addUser(request: UserRequest):Boolean
    suspend fun deleteUser(uid: Int, password: String): Boolean
    suspend fun updateUser(request: UpdatePasswordRequest): Boolean
}
