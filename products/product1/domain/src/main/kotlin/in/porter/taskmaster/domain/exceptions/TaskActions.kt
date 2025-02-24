package `in`.porter.taskmaster.domain.actions



import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.UpdateTaskStatusRequest
import javax.inject.Inject

interface TaskActions  {
    suspend fun getTasks(uid: Int): List<TaskRequest>
    suspend fun addTask(request: TaskRequest): Boolean
    suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean
    suspend fun deleteTask(uid: Int, uuid: Int, password: String): Boolean
}
