package `in`.porter.taskmaster.data.DatabaseRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest


interface TaskDbActions {


    suspend fun getTasksFromDb(userId: Int): List<TaskResponse>
    suspend fun addTaskInDb(request: TaskResponse): Boolean
    suspend fun deleteTaskFromDb(request:DeleteTaskRequest): Boolean
    suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean
}
