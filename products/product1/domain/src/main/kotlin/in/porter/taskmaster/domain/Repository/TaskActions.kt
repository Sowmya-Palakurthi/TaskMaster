package `in`.porter.taskmaster.domain

import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.TaskEntities.UpdateTaskStatusRequest


interface TaskActions {
    suspend fun getTasks(userid:Int): List<TaskRequest>
    suspend fun addTask(request:TaskRequest): Boolean
    suspend fun updateTask(request: UpdateTaskStatusRequest): Boolean
    suspend fun deleteTask(userid:Int, taskid:Int,password:String): Boolean

}



