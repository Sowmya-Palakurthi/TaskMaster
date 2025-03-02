package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject

class UpdatePasswordService  @Inject constructor(
    private val repository: UserRepositoryActions
) {
    suspend fun invoke(request: UpdatePasswordRequest): Boolean = repository.updateUser(request)
}