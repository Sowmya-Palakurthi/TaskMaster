package `in`.porter.taskmaster.servers.ktor.Handlers

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.DeleteApiTaskRequest
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.UpdateApiTaskStatusRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory
import `in`.porter.taskmaster.api.service.usecases.CreateTask
import `in`.porter.taskmaster.api.service.usecases.DeleteTask
import `in`.porter.taskmaster.api.service.usecases.GetTasks
import `in`.porter.taskmaster.api.service.usecases.UpdateTask
import javax.inject.Inject

class TaskHandler @Inject constructor(
    private val createTask: CreateTask,
    private val deleteTask: DeleteTask,
    private val updateTask: UpdateTask,
    private val getTasks: GetTasks
) {
    private val logger = LoggerFactory.getLogger(TaskHandler::class.java)

    suspend fun createTask(call: ApplicationCall) {
        try {
            val request = call.receive<TaskApiRequest>()
            logger.info("Creating task: $request")
            val createMsg=createTask.invoke(request)
            if(createMsg!="Task created successfully in the table"){
                call.respond(HttpStatusCode.BadRequest, createMsg)
            }
            else{
            call.respond(HttpStatusCode.Created, "Task created successfully ")}
        } catch (e: Exception) {
            handleException(call, "Error creating task", e)
        }
    }

    suspend fun getTasks(call: ApplicationCall) {
        try {
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                return
            }
            logger.info("Fetching tasks for user ID: $userId")
            val tasks = getTasks.invoke(userId)

            if (tasks!=null) {
                call.respond(HttpStatusCode.OK, tasks)
            } else {
                call.respond(HttpStatusCode.NotFound, "No tasks found for user ID: $userId")
            }
        } catch (e: Exception) {
            handleException(call, "Error fetching tasks", e)
        }
    }
    suspend fun deleteTask(call: ApplicationCall) {
        try {
            val request = call.receive<DeleteApiTaskRequest>()
            logger.info("Deleting task: $request")
            val deleteMsg=deleteTask.invoke(request)
            if(deleteMsg=="Task deleted successfully"){
                call.respond(HttpStatusCode.OK, deleteMsg)}
            else{
                call.respond(HttpStatusCode.BadRequest, deleteMsg)
            }
        } catch (e: Exception) {
            handleException(call, "Error deleting task", e)
        }
    }

    suspend fun updateTask(call: ApplicationCall) {
        try {
            val request = call.receive<UpdateApiTaskStatusRequest>()
            logger.info("Updating task: $request")
            val updateMsg=updateTask.invoke(request)
            if(updateMsg!="Task status updated successfully"){
                call.respond(HttpStatusCode.BadRequest, updateMsg)
            }
            else{
            call.respond(HttpStatusCode.OK, "Task updated successfully")}
        } catch (e: Exception) {
            handleException(call, "Error updating task", e)
        }
    }


    private suspend fun handleException(call: ApplicationCall, message: String, e: Exception) {
        logger.error(message, e)
        call.respond(HttpStatusCode.InternalServerError, message)
    }
}
