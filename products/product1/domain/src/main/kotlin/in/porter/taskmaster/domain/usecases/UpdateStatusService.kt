package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject



class UpdateStatusService @Inject constructor(
    private val repository: TaskRepositoryActions
) {
    suspend fun invoke(request: UpdateTaskStatusRequest): String {
        return try {
            val status=repository.updateTask(request)
            if(status){
            "Task status updated successfully"    }
            else{
                "Task not updated due to some issue "
            }
        } catch (e: Exception) {
            "Failed to update task status: ${e.message}"
        }
    }
}