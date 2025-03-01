package `in`.porter.taskmaster.data.mapperclasses

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest


interface TaskMapperActions {

    fun taskRequestToResponse(request: TaskRequest): TaskResponse
    fun taskResponseToRequest(response: TaskResponse): TaskRequest
}