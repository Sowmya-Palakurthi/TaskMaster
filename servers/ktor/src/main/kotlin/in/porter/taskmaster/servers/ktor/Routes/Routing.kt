package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.TaskRepositoryActions
import `in`.porter.taskmaster.domain.UserRepositoryActions
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(userService: UserRepositoryActions, taskService:TaskRepositoryActions, TaskMapper: TaskMapperActions) {
    routing {
        userRoutes(userService)
        TaskRoutes(taskService, TaskMapper )
    }
}
