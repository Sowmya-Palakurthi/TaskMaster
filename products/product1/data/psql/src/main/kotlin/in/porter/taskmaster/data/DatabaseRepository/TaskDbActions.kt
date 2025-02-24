package `in`.porter.taskmaster.data.DatabaseRepository

import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.UpdateTaskStatusRequest


interface TaskDbActions {

    suspend fun getTasksFromDb(userId: Int): List<TaskRequest>
    suspend fun addTaskInDb(request: TaskRequest): Boolean
    suspend fun deleteTaskFromDb(userId: Int, password: String, taskId: Int): Boolean
    suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean
}
