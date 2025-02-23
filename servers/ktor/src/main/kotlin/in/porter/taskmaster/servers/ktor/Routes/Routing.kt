package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.domain.Repository.UserActions
import io.ktor.server.application.*
import io.ktor.server.routing.*
import java.security.Security

fun Application.configureRouting(userService: UserActions, security: Security) {
    routing {
        userRoutes(userService)
        //TaskRoutes(taskService, taskMapper,security )
    }
}
