package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject


class CreateUserService @Inject constructor(private val userRepository: UserRepositoryActions) {

    suspend fun invoke(request: UserRequest)  = userRepository.addUser(request)

}