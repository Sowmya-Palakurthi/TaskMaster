package `in`.porter.taskmaster.data.RepositoryImplementation



import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.domain.exceptions.UserActions
import javax.inject.Inject
import org.slf4j.LoggerFactory

//private val logger = LoggerFactory.getLogger(UserService::class.java)
class UserRepositoryImplementation @Inject constructor(
    private val redisCache: UserRedisActions,
    private val userDb: UserDbActions // Inject database actions
) : UserActions {

    override suspend fun getUser(userId: Int): UserResponse? {
        val cachedUser = redisCache.getUserData(userId)
        if (cachedUser != null) return cachedUser

        val userFromDb = userDb.getUserFromDb(userId)
        userFromDb?.let {
            redisCache.setUserData(Json.encodeToString(it), 3600, userId) // Cache for 1 hour
        }

        return userFromDb
    }

    override suspend fun addUser(request: UserRequest): Boolean {
        return try {
            if (request.getUserId().toString().isBlank() ||
                request.getUsername().isBlank() ||
                request.getPassword().isBlank()
            ) {
                return false
            }
            val inserted = userDb.addUserInDb(request)
            if (inserted) {
                redisCache.addUserData(request)
            }
            else{
               // logger.warn("Could not add the user")
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
        //else logger.warn("Error in deleting the user")

        return deleted
    }

    override suspend fun updateUser(request: UpdatePasswordRequest): Boolean {
        val updated = userDb.updatePasswordInDb(request)
        if (!updated) {
            //logger.warn("Error: The credentials provided do not match.")
            return false
        }
        redisCache.deleteUserData(request.getUserId())
        return true
    }

    suspend fun isPasswordValidForUser(userId: Int, password: String): Boolean {
        return userDb.isPasswordValidForUser(userId, password)
    }
}
