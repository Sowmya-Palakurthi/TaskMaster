package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.data.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.exceptions.UserActions
import `in`.porter.taskmaster.domain.exceptions.MissingEmailIdException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

fun Route.userRoutes(userService: UserActions) {
    val logger = LoggerFactory.getLogger("UserRoutes")

    route("/users") {

        get("/{userId}") {
            try {
                val userId = call.parameters["userId"]?.toIntOrNull()
                    ?: throw MissingEmailIdException("Missing or invalid user ID")

                val user = userService.getUser(userId)
                if (user != null) {
                    call.respond(HttpStatusCode.OK, user)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User with ID '$userId' not found")
                }
            } catch (e: MissingEmailIdException) {
                logger.error("Error: ${e.message}")
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Bad Request")
            } catch (e: Exception) {
                logger.error("Error fetching user: ${e.localizedMessage}", e)
                call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
            }
        }

        // Add a new user
        post("/add") {
            try {
                val userRequest = call.receive<UserRequest>()

                val isAdded= userService.addUser(userRequest)

                if (isAdded) {
                    call.respond(HttpStatusCode.Created, "User created successfully")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "User could not be added")
                }
            } catch (e: Exception) {
                logger.error("Error adding user: ${e.localizedMessage}", e)
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Error occurred")
            }
        }

        // Delete user
        delete("/{userId}/{password}") {
            try {
                val userId = call.parameters["userId"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid user ID")

                val password = call.parameters["password"] ?: return@delete call.respond(
                    HttpStatusCode.BadRequest, "Password is required"
                )

                if (password.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Password cannot be empty")
                    return@delete
                }

                val isDeleted = userService.deleteUser(userId, password)
                if (isDeleted) {
                    call.respond(HttpStatusCode.OK, "User deleted successfully")
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid user ID or password")
                }
            } catch (e: Exception) {
                logger.error("Error deleting user: ${e.localizedMessage}", e)
                call.respond(HttpStatusCode.InternalServerError, "Error deleting user")
            }
        }

        // Update password
        put("/update-password") {
            try {
                val updatePasswordRequest = call.receive<UpdatePasswordRequest>()
                val userId = updatePasswordRequest.getUserId()
                val currentPassword = updatePasswordRequest.getCurrentPassword()
                val newPassword = updatePasswordRequest.getNewPassword()

                if (userId.toString().isBlank() || currentPassword.isBlank() || newPassword.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "All fields are required")
                    return@put
                }

                if (currentPassword == newPassword) {
                    call.respond(HttpStatusCode.BadRequest, "Current and new password cannot be the same")
                    return@put
                }

                val isUpdated = userService.updateUser(updatePasswordRequest)
                if (isUpdated) {
                    call.respond(HttpStatusCode.OK, "Password updated successfully")
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid user ID or current password")
                }
            } catch (e: Exception) {
                logger.error("Error updating password: ${e.localizedMessage}", e)
                call.respond(HttpStatusCode.InternalServerError, "Error updating password")
            }
        }
    }
}
