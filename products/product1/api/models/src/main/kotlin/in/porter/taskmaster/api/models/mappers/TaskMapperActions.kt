package `in`.porter.taskmaster.api.models.mappers

import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskResponse

interface TaskMapperActions {

    fun taskRequestToResponse(request:TaskRequest):TaskResponse
    fun taskResponseToRequest(response:TaskResponse): TaskRequest

}