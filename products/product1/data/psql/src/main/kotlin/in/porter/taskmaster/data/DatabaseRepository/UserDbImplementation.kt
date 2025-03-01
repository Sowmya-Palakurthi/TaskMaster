package `in`.porter.taskmaster.data.DatabaseRepository



import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity.users.uid
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class UserDbImplementation @Inject constructor() : UserDbActions {

    override suspend fun getUserFromDb(userId: Int): UserResponse? {
        return transaction {
            UserEntity.users.select { uid eq userId }
                .singleOrNull()?.let {
                    UserResponse(
                        uid = it[uid],
                        username = it[UserEntity.users.username],

                    )
                }
        }
    }

    override suspend fun addUserInDb(request: UserRequest): Boolean {

        return transaction {
            UserEntity.users.insert {
                it[uid] = request.uid
                it[username] = request.username
                it[password] = request.password
            }
            true
        }
    }

    override suspend fun deleteUserFromDb(userId: Int,password: String): Boolean {
        return transaction {
            val user = UserEntity.users.select { uid eq userId }.singleOrNull()
            if (user != null && user[UserEntity.users.password] == password) {
                val deletedRows = UserEntity.users.deleteWhere { uid eq uid }
                deletedRows > 0
            } else {
                false
            }
        }
    }

    override suspend fun updatePasswordInDb(request: UpdatePasswordRequest): Boolean {
        return transaction {
            val updatedRows = UserEntity.users.update({ uid eq request.uid and (UserEntity.users.password eq request.currentPassword) }) {
                it[password] = request.newPassword
            }
            updatedRows > 0
        }
    }

    override suspend fun isPasswordValidForUser(userId: Int, password: String): Boolean {
        return transaction {
            UserEntity.users.select { uid eq userId and (UserEntity.users.password eq password) }
                .count() > 0
        }
    }
}
