package `in`.porter.taskmaster.domain.RepositoryActions

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest

interface TaskRepositoryActions {
    suspend fun getTasks(uid:Int): List<TaskResponse>
    suspend fun addTask(request: TaskResponse): Boolean
    suspend fun updateTask(request: UpdateTaskStatusRequest): Boolean
    suspend fun deleteTask(deleteTaskRequest: DeleteTaskRequest): Boolean

}

