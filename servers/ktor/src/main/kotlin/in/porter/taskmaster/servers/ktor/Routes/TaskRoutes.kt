package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.api.models.mappers.TaskMapper
import `in`.porter.taskmaster.domain.TaskActions
import `in`.porter.taskmaster.domain.TaskEntities.TaskResponse

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.security.Security

fun Route.TaskRoutes(taskService: TaskActions, taskMapper: TaskMapper, security: Security) {

    route("/tasks") {
        get("/"){
            call.respondText("Hello, World!")
        }
        get("/{userId}") {
            try {
                val userId = call.parameters["userId"]?.toInt() ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid user ID"
                )
                val tasks = taskService.getTasks(userId)
                if (tasks.isNotEmpty()) {
                    call.respond(HttpStatusCode.OK, tasks)
                } else {
                    call.respond(HttpStatusCode.NotFound, "No tasks found for user with ID $userId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, "Error fetching tasks.")
            }
        }

        post("/add") {
            try {
                val taskResponse = call.receive<TaskResponse>()
                val isAdded = taskService.addTask(taskMapper.responseToRequest(taskResponse))
                if (isAdded) {
                    call.respond(HttpStatusCode.Created, "Task added successfully.")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Task not added due to some issue")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, "Error adding task.")
            }
        }


        delete("/{taskId}/{userid}/{password}") {
            try {
                val userId = call.parameters["userid"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token or user not found.")
                    return@delete
                }

                val taskId = call.parameters["taskId"]?.toIntOrNull()
                val password = call.parameters["password"]

                if (taskId == null || password.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Task ID and password are required.")
                    return@delete
                }

                val isDeleted = taskService.deleteTask(userId, taskId, password)
                if (isDeleted) {
                    call.respond(HttpStatusCode.OK, "Task deleted successfully.")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task not found.")
                }
            } catch (e: Exception) {
                call.handleException(e, "Error deleting task.")
            }
        }

        put("/update-status") {

            try {
                val request = call.receive<UpdateTaskStatusRequest>()

                val isUpdated = taskService.updateTask(request)
                if (isUpdated) {
                    call.respond(HttpStatusCode.OK, "Task status updated successfully.")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task not found.")
                }
            } catch (e: Exception) {
                call.handleException(e, "Error updating task status.")
            }
        }

    }
}


private suspend fun ApplicationCall.handleException(e: Exception, message: String) {
    e.printStackTrace()
    respond(HttpStatusCode.InternalServerError, message)
}

