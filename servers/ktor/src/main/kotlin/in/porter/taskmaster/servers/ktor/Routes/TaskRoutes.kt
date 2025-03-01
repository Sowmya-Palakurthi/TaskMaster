package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest

import `in`.porter.taskmaster.domain.TaskRepositoryActions

import io.ktor.http.*
import io.ktor.server.application.*

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Route.TaskRoutes(taskService: TaskRepositoryActions, taskMapper: TaskMapperActions) {

    route("/tasks") {

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
                val taskResponse = call.receive<TaskRequest>()
                val isAdded = taskService.addTask(taskMapper.taskRequestToResponse(taskResponse))
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


        delete("/deletetask") {


                try {
                        val taskInput = call.receive<DeleteTaskRequest>()
                        val isDeleted = taskService.deleteTask(taskInput)

                    if (isDeleted) {
                        call.respond(HttpStatusCode.Created, "Task deleted successfully.")
                    } else {
                        call.respond(HttpStatusCode.BadRequest, "Task not deleted due to some issue")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    call.respond(HttpStatusCode.InternalServerError, "Error adding task.")
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

