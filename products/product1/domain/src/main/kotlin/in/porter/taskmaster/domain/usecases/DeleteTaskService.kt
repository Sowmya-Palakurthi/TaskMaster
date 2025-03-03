package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject



class DeleteTaskService @Inject constructor(
    private val repository: TaskRepositoryActions
) {
    suspend fun invoke(name: DeleteTaskRequest): String {
        return try {
           val status= repository.deleteTask(name)
            if(status)
            { return "Task deleted successfully"}
            else
            { return  "Task is not deleted due to some issue"}
        } catch (e: Exception) {
            "Failed to delete task: ${e.message}"
        }
    }
}