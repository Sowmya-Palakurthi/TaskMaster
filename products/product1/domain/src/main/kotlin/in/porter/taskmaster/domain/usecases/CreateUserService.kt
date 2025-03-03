package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject


class CreateUserService @Inject constructor(private val userRepository: UserRepositoryActions) {

    suspend fun invoke(request: UserRequest): String {
        return try {
            val status=userRepository.addUser(request)
            if(status){
                return "User created successfully"
            }
          else{
              return "User is not added due to some issue"
            }

        } catch (e: Exception) {
            "Failed to create user: ${e.message}"
        }
    }
}