package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject

class GetUserService @Inject constructor(
    private val repository: UserRepositoryActions
) {
    suspend fun invoke(uid: Int): UserResponse? = repository.getUser(uid)
}