package `in`.porter.taskmaster.domain.MapperInterfaces

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse


interface TaskMapperActions {

    fun taskRequestToResponse(request: TaskRequest): TaskResponse
    fun taskResponseToRequest(response: TaskResponse): TaskRequest
}