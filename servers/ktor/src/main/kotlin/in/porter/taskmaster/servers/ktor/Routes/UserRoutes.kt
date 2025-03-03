package `in`.porter.taskmaster.servers.ktor.Routes
//
//import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
//import io.ktor.http.*
//import io.ktor.server.application.*
//import io.ktor.server.request.*
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//import org.slf4j.LoggerFactory
//
//fun Route.userRoutes(userHandler: UserHandler) {
//    val logger = LoggerFactory.getLogger("UserRoutes")
//
//    route("/users") {
//
//        get("/{userId}") {
//            try {
//                userHandler.getUser(call)
//            } catch (e: Exception) {
//                logger.error("Error fetching user: ${e.localizedMessage}", e)
//                call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred")
//            }
//        }
//
//
//        post("/add") {
//            try {
//                userHandler.addUser(call)
//            } catch (e: Exception) {
//                logger.error("Error adding user: ${e.localizedMessage}", e)
//                call.respond(HttpStatusCode.InternalServerError, "Error occurred")
//            }
//        }
//
//        delete("/deleteuser") {
//            try {
//                userHandler.deleteUser(call)
//            } catch (e: Exception) {
//                logger.error("Error deleting user: ${e.localizedMessage}", e)
//                call.respond(HttpStatusCode.InternalServerError, "Error deleting user")
//            }
//        }
//
//        put("/update-password") {
//            try {
//                userHandler.updatePassword(call)
//            } catch (e: Exception) {
//                logger.error("Error updating password: ${e.localizedMessage}", e)
//                call.respond(HttpStatusCode.InternalServerError, "Error updating password")
//            }
//        }
//    }
//}


import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.userRoutes(userHandler: UserHandler) {
    route("/users") {

        get("/{userId}") {
            userHandler.getUser(call)
        }

        post("/add") {
            userHandler.addUser(call)
        }

        delete("/deleteuser") {
            userHandler.deleteUser(call)
        }

        put("/update-password") {
            userHandler.updatePassword(call)
        }
    }
}

