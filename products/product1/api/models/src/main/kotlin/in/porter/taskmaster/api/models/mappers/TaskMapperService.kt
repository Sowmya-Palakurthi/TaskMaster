package `in`.porter.taskmaster.api.models.mappers


import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskResponse
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject






class TaskMapperService @Inject constructor() : TaskMapperActions {

    override fun taskResponseToRequest(response: TaskResponse): TaskRequest {

        val currentInstant = Instant.now() // Get the current timestamp
        val duration = Duration.between(response.getDeadline(), currentInstant)
        val differenceInDays = duration.toDays()


        return TaskRequest(
            uuid = response.getTaskId(), // Changed from taskid to uuid
            uid = response.getUserId(),  // Changed from userid to uid
            title = response.getTitle(),
            description = response.getDescription(),
            isdone = response.getIsDone(),
            deadline = differenceInDays // Now using Instant directly
        )
    }

    override fun taskRequestToResponse(request: TaskRequest): TaskResponse {

        val secondsToAdd: Long = 86400 // Example: Add 1 day (86400 seconds)

        val currentInstant = Instant.now()
        val newInstant = currentInstant.plusSeconds(secondsToAdd) // Add seconds to current time
        return TaskResponse(
            uuid = request.getTaskId(),  // Changed from taskid to uuid
            uid = request.getUserId(),   // Changed from userid to uid
            title = request.getTitle(),
            description = request.getDescription(),
            isdone = request.getIsDone(),
            deadline = newInstant // Now using Instant directly
        )
    }
}
