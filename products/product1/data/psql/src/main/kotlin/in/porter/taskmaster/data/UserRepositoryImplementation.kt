package `in`.porter.taskmaster.data

import org.slf4j.LoggerFactory
import `in`.porter.taskmaster.domain.UserRepositoryActions


import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import javax.inject.Inject


class UserRepositoryImplementation @Inject constructor(
    private val redisCache: UserRedisActions,
    private val userDb: UserDbActions
) : UserRepositoryActions {
    val logger: Logger = LoggerFactory.getLogger(UserRepositoryActions::class.java)

    override suspend fun getUser(userId: Int): UserResponse? {
        val cachedUser = redisCache.getUserData(userId)
        if (cachedUser != null) return cachedUser

        val userFromDb = userDb.getUserFromDb(userId)
        userFromDb?.let {
            redisCache.setUserData(Json.encodeToString(it), 3600, userId)
        }

        return userFromDb
    }

    override suspend fun addUser(request: UserRequest): Boolean {
        return try {
            if (request.uid.toString().isBlank() ||
                request.username.isBlank() ||
                request.password.isBlank()
            ) {
                return false
            }
            val inserted = userDb.addUserInDb(request)
            if (inserted) {
                redisCache.addUserData(request)
            }
            else{
                logger.warn("Could not add the user")
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun deleteUser(userId: Int,password: String): Boolean {
        val deleted = userDb.deleteUserFromDb(userId,password)
        if (deleted) redisCache.deleteUserData(userId)
        else logger.warn("Error in deleting the user")

        return deleted
    }

    override suspend fun updateUser(request: UpdatePasswordRequest): Boolean {
        val updated = userDb.updatePasswordInDb(request)
        if (!updated) {
            logger.warn("Error: The credentials provided do not match.")
            return false
        }
        redisCache.deleteUserData(request.uid)
        return true
    }
//
//    suspend fun isPasswordValidForUser(userId: Int, password: String): Boolean {
//        return userDb.isPasswordValidForUser(userId, password)

}