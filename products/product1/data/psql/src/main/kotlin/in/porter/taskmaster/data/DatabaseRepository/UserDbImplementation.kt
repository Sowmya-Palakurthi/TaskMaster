package `in`.porter.taskmaster.data.DatabaseRepository



import `in`.porter.taskmaster.data.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserEntity
import `in`.porter.taskmaster.data.Entities.UserEntities.UserEntity.users.userid
import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class UserDbImplementation @Inject constructor() : UserDbActions {

    val userObj = UserEntity.users // Access user table

    override suspend fun getUserFromDb(userId: Int): UserResponse? {
        return transaction {
            userObj.select { userObj.uid eq userId }
                .singleOrNull()?.let {
                    UserResponse(
                        uid = it[userObj.uid],
                        username = it[userObj.username],

                    )
                }
        }
    }

    override suspend fun addUserInDb(request: UserRequest): Boolean {

        return transaction {
            userObj.insert {
                it[uid] = request.getUserId()
                it[username] = request.getUsername()
                it[password] = request.getPassword()
            }
            true
        }
    }

    override suspend fun deleteUserFromDb(userId: Int,password: String): Boolean {
        return transaction {
            val user = userObj.select { userObj.uid eq userId }.singleOrNull()
            if (user != null && user[userObj.password] == password) {
                val deletedRows = userObj.deleteWhere { userObj.uid eq uid }
                deletedRows > 0
            } else {
                false
            }
        }
    }

    override suspend fun updatePasswordInDb(request: UpdatePasswordRequest): Boolean {
        return transaction {
            val updatedRows = userObj.update({ userObj.userid eq request.getUserId() and (userObj.password eq request.getCurrentPassword()) }) {
                it[password] = request.getNewPassword()
            }
            updatedRows > 0
        }
    }

    override suspend fun isPasswordValidForUser(userId: Int, password: String): Boolean {
        return transaction {
            userObj.select { userObj.userid eq userId and (userObj.password eq password) }
                .count() > 0
        }
    }
}
