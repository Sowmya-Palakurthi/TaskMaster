package `in`.porter.taskmaster.servers.ktor.Routes


import `in`.porter.taskmaster.servers.ktor.Handlers.TaskHandler
import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(taskHandler: TaskHandler,userHandler: UserHandler) {
    routing {
        userRoutes(userHandler)
        TaskRoutes(taskHandler)
    }
}
