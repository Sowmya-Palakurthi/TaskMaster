package `in`.porter.taskmaster.data.DatabaseRepository

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskEntity
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserEntity
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDate as JavaLocalDate
import java.time.format.DateTimeFormatter
import kotlinx.datetime.LocalDate as KotlinxLocalDate
import javax.inject.Inject
import org.jetbrains.exposed.sql.and

class TaskDbImplementation @Inject constructor(private val taskMapper: TaskMapperActions) : TaskDbActions {
    private val taskObj = TaskEntity.tasks
    private val userObj = UserEntity.users

    override suspend fun getTasksFromDb(userId: Int): List<TaskResponse> {

        return transaction {
            taskObj.select { taskObj.uid eq userId }.map {
                TaskResponse(
                    uuid = it[taskObj.uuid],
                    uid = it[taskObj.uid],
                    title = it[taskObj.title],
                    description = it[taskObj.description],
                    isdone = it[taskObj.isdone],
                    deadline =it[taskObj.deadline].toKotlinLocalDate()
                )
            }
        }


    }

    override suspend fun addTaskInDb(request: TaskResponse): Boolean {
        return transaction {
            if(request.uuid.toString().isBlank()){
                println("Error: TaskID cannot be null.")
                return@transaction false
            }
            if(request.uuid.toString().isBlank()){
                println("Error: UserID cannot be null.")
                return@transaction false
            }

            taskObj.insert {
                it[uuid] = request.uuid
                it[uid] = request.uid
                it[title] = request.title
                it[description] = request.description
                it[deadline] = JavaLocalDate.of(request.deadline.year, request.deadline.monthNumber, request.deadline.dayOfMonth)
                it[isdone] = request.isdone
            }
            true
        }
    }

    override suspend fun deleteTaskFromDb(deleteTaskRequest: DeleteTaskRequest): Boolean {
        return transaction {

            val isValidPassword = userObj
                .select { userObj.uid eq deleteTaskRequest.uid and (userObj.password eq deleteTaskRequest.password) }
                .count() > 0

            if (isValidPassword) {
                val deletedRows = taskObj.deleteWhere {
                    (taskObj.uid eq deleteTaskRequest.uid) and (taskObj.uuid eq deleteTaskRequest.uuid)
                }
                deletedRows > 0
            } else {
                // Invalid password
                false
            }
        }
    }

    override suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean {
        return transaction {
            val updatedRows = taskObj.update({
                (taskObj.uid eq request.uid) and (taskObj.uuid eq request.uuid)

            }) {
                it[isdone] = request.isdone
            }
            if(updatedRows > 0){
                return@transaction true
            }
            return@transaction false
        }
    }
}
