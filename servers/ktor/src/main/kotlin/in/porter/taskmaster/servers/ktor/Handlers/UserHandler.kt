package `in`.porter.taskmaster.servers.ktor.Handlers

import com.google.gson.Gson
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.*
import `in`.porter.taskmaster.api.service.usecases.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory
import javax.inject.Inject

class UserHandler @Inject constructor(
    private val addUser: CreateUser,
    private val deleteUser: DeleteUser,
    private val updateUser: UpdateUser,
    private val getUser: GetUser
) {
    private val logger = LoggerFactory.getLogger(UserHandler::class.java)

    suspend fun getUser(call: ApplicationCall) {
        try {
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                return
            }
            logger.info("Fetching user with ID: $userId")
            val user = getUser.invoke(userId)
            if (user != null) {
                val gson = Gson()
                val userJson = gson.toJson(user)
                call.respond(HttpStatusCode.OK, userJson)
                logger.info("Final response sent to client: $userJson")
            } else {
                call.respond(HttpStatusCode.NotFound, "User with ID '$userId' not found")
            }
        } catch (e: Exception) {
            logger.error("Error fetching user: ${e.localizedMessage}", e)
            handleException(call, "Error fetching user", e)
            call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
        }
    }

    suspend fun addUser(call: ApplicationCall) {
        try {
            val request = call.receive<UserApiRequest>()
            logger.info("Creating user: $request")
            try {
                println(request)
                val addMsg = addUser.invoke(request)
                if (addMsg != "User created successfully") {
                    call.respond(HttpStatusCode.BadRequest, addMsg)
                } else {
                    call.respond(HttpStatusCode.Created, "User created successfully")
                }
            } catch (e: Exception) {
                logger.error("Error adding user: ${e.localizedMessage}", e)
                call.respond(HttpStatusCode.InternalServerError, "An error occurred while adding the user")
            }
        } catch (e: Exception) {
            handleException(call, "Error adding user", e)
            call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
        }
    }


    suspend fun deleteUser(call: ApplicationCall) {
        try {
            val request = call.receive<DeleteApiUserRequest>()
            if(request.uid == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                return
            }
            logger.info("Deleting user: $request")
             val deleteMsg=deleteUser.invoke(request)
            if(deleteMsg == "User deleted successfully"){
                call.respond(HttpStatusCode.OK, deleteMsg)
            }
            else{
                call.respond(HttpStatusCode.BadRequest, deleteMsg)
            }

        } catch (e: Exception) {
            handleException(call, "Error deleting user", e)
            call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")

        }
    }

    suspend fun updatePassword(call: ApplicationCall) {
        try {
            val request = call.receive<UpdateApiPasswordRequest>()
            logger.info("Updating password for user ID: ${request.uid}")

           val passwordMsg= updateUser.invoke(request)
           if(passwordMsg!="Password updated successfully"){
               call.respond(HttpStatusCode.BadRequest, passwordMsg)}
            else{
                call.respond(HttpStatusCode.OK, "Password updated successfully")
           }
        } catch (e: Exception) {
            handleException(call, "Error updating password", e)
        }
    }

    private suspend fun handleException(call: ApplicationCall, message: String, e: Exception) {
        logger.error("$message: ${e.localizedMessage}", e)
        call.respond(HttpStatusCode.InternalServerError, message)
    }
}
