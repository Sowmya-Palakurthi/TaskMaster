package `in`.porter.taskmaster.api.service.mappers


import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject


class TaskMapperService @Inject constructor() : TaskMapperActions {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


    override fun taskResponseToRequest(response: TaskResponse): TaskRequest {
        val currentDate = LocalDate.now()
        val deadlineDate = currentDate.plusDays(response.getDeadline()) // Convert days left to actual date

        return TaskRequest(
            uuid = response.getTaskId(),
            uid = response.getUserId(),
            title = response.getTitle(),

            description = response.getDescription(),
            isdone = response.getIsDone(),
            deadline = deadlineDate.toString() // Convert LocalDate to formatted string
        )
    }

    override fun taskRequestToResponse(request: TaskRequest): TaskResponse {
        // Parse the deadline string into LocalDate
        val parsedDeadlineDate = try {
            LocalDate.parse(request.getDeadline(), dateFormatter)
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid date format: '${request.getDeadline()}'. Expected format: dd-MM-yyyy")
        }
        val currentDate = LocalDate.now()
        val daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, parsedDeadlineDate) // Convert date to days left

        return TaskResponse(
            taskid = request.getTaskId(),
            userid = request.getUserId(),
            title = request.getTitle(),
            description = request.getDescription(),
            isdone = request.getIsDone(),
            deadline = daysUntilDeadline // Store remaining days as Long
        )
    }
}



















