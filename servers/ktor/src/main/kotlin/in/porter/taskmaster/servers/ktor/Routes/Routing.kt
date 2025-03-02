package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(userService: UserRepositoryActions, taskService: TaskRepositoryActions, TaskMapper: TaskMapperActions) {
    routing {
        userRoutes(userService)
        TaskRoutes(taskService, TaskMapper )
    }
}
