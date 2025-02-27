package `in`.porter.taskmaster.domain

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest

interface TaskRepositoryActions {
    suspend fun getTasks(uid:Int): List<TaskResponse>
    suspend fun addTask(request: TaskRequest): Boolean
    suspend fun updateTask(request: UpdateTaskStatusRequest): Boolean
    suspend fun deleteTask(uid:Int, uuid:Int,password:String): Boolean

}

