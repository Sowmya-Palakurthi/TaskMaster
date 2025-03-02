package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.DeleteUserRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject



class DeleteUserService @Inject constructor(
    private val repository: UserRepositoryActions
) {
    suspend fun invoke(request: DeleteUserRequest): Boolean = repository.deleteUser(request.uid,request.password)
}
