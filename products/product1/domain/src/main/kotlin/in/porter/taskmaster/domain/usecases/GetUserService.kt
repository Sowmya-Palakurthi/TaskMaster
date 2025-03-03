package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject
import org.slf4j.LoggerFactory


class GetUserService @Inject constructor(
    private val repository: UserRepositoryActions
) {

    private val logger = LoggerFactory.getLogger(GetUserService::class.java)

    suspend fun invoke(uid: Int): UserResponse? {
       try {
            val user = repository.getUser(uid)
            logger.info("User fetched in GetUserService: $user")
            return user
        } catch (e: Exception) {
            logger.error("Error fetching user with ID $uid: ${e.localizedMessage}", e)
            return null
        }
    }




}

