package `in`.porter.taskmaster.data.MapperClasses

import kotlinx.datetime.*
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import javax.inject.Inject

class TaskMapperService @Inject constructor() : TaskMapperActions {

    override fun taskResponseToRequest(response: TaskResponse): TaskRequest {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val daysUntilDeadline = currentDate.daysUntil(response.deadline).toLong()
        return TaskRequest(
            uuid = response.uuid,
            uid = response.uid,
            title = response.title,
            description = response.description,
            isdone = response.isdone,
            deadline = daysUntilDeadline
        )
    }

    override fun taskRequestToResponse(request: TaskRequest): TaskResponse {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val deadlineDate = currentDate.plus(request.deadline, DateTimeUnit.DAY)

        return TaskResponse(
            uuid = request.uuid,
            uid = request.uid,
            title = request.title,
            description = request.description,
            isdone = request.isdone,
            deadline = deadlineDate
        )
    }
}
