package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject



class UpdatePasswordService @Inject constructor(
    private val repository: UserRepositoryActions
) {
    suspend fun invoke(request: UpdatePasswordRequest): String {
        return try {
           val status= repository.updateUser(request)
            if(status){
                return "Password updated successfully"
            }
            else{
                return " check the data you have entered"
            }
        } catch (e: Exception) {
            "Failed to update password: ${e.message}"
        }
    }
}