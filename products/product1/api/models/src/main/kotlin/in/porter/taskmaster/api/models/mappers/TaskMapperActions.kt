package `in`.porter.taskmaster.api.models.mappers

import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskResponse


interface TaskMapperActions {

    fun taskRequestToResponse(request: TaskRequest):TaskResponse
    fun taskResponseToRequest(response: TaskResponse): TaskRequest

}