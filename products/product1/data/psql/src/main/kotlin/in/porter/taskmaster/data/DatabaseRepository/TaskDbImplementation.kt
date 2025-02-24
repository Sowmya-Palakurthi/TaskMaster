package `in`.porter.taskmaster.data.DatabaseRepository

import `in`.porter.taskmaster.api.models.mappers.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.servers.commons.Entities.TaskEntities.TaskEntity
import `in`.porter.taskmaster.servers.commons.Entities.UserEntities.UserEntity

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import javax.inject.Inject
import org.jetbrains.exposed.sql.and
import java.time.Duration
import java.time.Instant

class TaskDbImplementation @Inject constructor(private val taskMapper: TaskMapperActions) : TaskDbActions {
    private val taskObj = TaskEntity.tasks
    private val userObj = UserEntity.users
    val currentInstant = Instant.now() // Get the current timestamp

    override suspend fun getTasksFromDb(userId: Int): List<TaskRequest> {




        return transaction {
            taskObj.select { taskObj.uid eq userId }.map {
                TaskRequest(
                    uuid = it[taskObj.uuid], // Changed from taskid to uuid
                    uid = it[taskObj.uid],   // Changed from userid to uid
                    title = it[taskObj.title],
                    description = it[taskObj.description],
                    isdone = it[taskObj.isdone],
                    deadline = Duration.between(it[taskObj.deadline], currentInstant).toDays()// Convert timestamp to Instant
                )
            }
        }
    }

    override suspend fun addTaskInDb(req: TaskRequest): Boolean {
        return transaction {
            if (req.getTaskId().toString().isBlank()) {
                println("Error: TaskID cannot be null.")
                return@transaction false
            }
            if (req.getUserId().toString().isBlank()) {
                println("Error: UserID cannot be null.")
                return@transaction false
            }

            val response = taskMapper.taskRequestToResponse(req)
            if (response.getDeadline().epochSecond < 0) { // Updated deadline check for Instant
                println("Error: Deadline should not be negative.")
                return@transaction false
            }
            val secondsToAdd: Long = 86400 // Example: Add 1 day (86400 seconds)

            val currentInstant = Instant.now()
              // A
            taskObj.insert {
                it[uuid] = req.getTaskId()   // Changed from taskid to uuid
                it[uid] = req.getUserId()    // Changed from userid to uid
                it[title] = req.getTitle()
                it[description] = req.getDescription()
                it[deadline] = currentInstant.plusSeconds(secondsToAdd)// Convert Instant to timestamp
                it[isdone] = req.getIsDone()
            }
            true
        }
    }

    override suspend fun deleteTaskFromDb(userId: Int, password: String, taskId: Int): Boolean {
        return transaction {
            val isValidPassword = userObj
                .select { userObj.uid eq userId and (userObj.password eq password) } // Changed userid to uid
                .count() > 0

            if (isValidPassword) {
                val deletedRows = taskObj.deleteWhere {
                    (taskObj.uid eq userId) and (taskObj.uuid eq taskId) // Changed userid & taskid to uid & uuid
                }
                deletedRows > 0
            } else {
                false
            }
        }
    }

    override suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean {
        return transaction {
            val updatedRows = taskObj.update({
                (taskObj.uid eq request.getUserId()) and (taskObj.uuid eq request.getTaskId()) // Changed userid & taskid to uid & uuid
            }) {
                it[isdone] = request.isDone()
            }
            updatedRows > 0
        }
    }
}
