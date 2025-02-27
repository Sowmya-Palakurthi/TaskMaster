package `in`.porter.taskmaster.data.mapperclasses

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class TaskMapperService @Inject constructor() : TaskMapperActions {

    override fun taskResponseToRequest(response: TaskResponse): TaskRequest {
//        val currentInstant = Instant.now()
//        val deadlineInstant = currentInstant.plus(response.getDeadline(), ChronoUnit.DAYS) // Convert days left to actual Instant

        val currentDate = LocalDate.now()
        val daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, response.deadline)

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
       // Convert Instant to days left
        val currentDate = LocalDate.now()
        val deadlineDate = currentDate.plusDays(request.deadline.toLong())
        // Convert days left to actual Instant

        return TaskResponse(
            uuid = request.uuid,
            uid = request.uid,
            title = request.title,
            description = request.description,
            isdone = request.isdone,
            deadline = deadlineDate// Store remaining days as Long
        )
    }
}
