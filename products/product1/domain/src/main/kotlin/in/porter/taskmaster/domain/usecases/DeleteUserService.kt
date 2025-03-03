package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.DeleteUserRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject


class DeleteUserService @Inject constructor(
    private val repository: UserRepositoryActions
) {
    suspend fun invoke(request: DeleteUserRequest): String {
        return try {
            val status=repository.deleteUser(request.uid, request.password)
            if(status){
                return "User deleted successfully"
            }
            else{
               return "User not deleted due to some issue"
            }
        } catch (e: Exception) {
            "Failed to delete user: ${e.message}"
        }
    }
}
