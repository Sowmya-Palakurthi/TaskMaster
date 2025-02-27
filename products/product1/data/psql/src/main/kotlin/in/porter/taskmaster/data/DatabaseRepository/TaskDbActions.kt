package `in`.porter.taskmaster.data.DatabaseRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest


interface TaskDbActions {


    suspend fun getTasksFromDb(userId: Int): List<TaskResponse>
    suspend fun addTaskInDb(request: TaskResponse): Boolean
    suspend fun deleteTaskFromDb(userId: Int, password: String, taskId: Int): Boolean
    suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean
}
