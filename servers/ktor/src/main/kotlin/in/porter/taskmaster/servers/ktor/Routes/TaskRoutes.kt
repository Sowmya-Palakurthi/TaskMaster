package `in`.porter.taskmaster.servers.ktor.Routes

import `in`.porter.taskmaster.servers.ktor.Handlers.TaskHandler
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.TaskRoutes(taskHandler: TaskHandler) {
    route("/tasks") {
        get("/{userId}") {
            taskHandler.getTasks(call)
        }
        post("/add"){
            taskHandler.createTask(call)
        }
        delete("/deletetask") {
            taskHandler.deleteTask(call)
        }
        put("/update-status") {
            taskHandler.updateTask(call)
        }
    }
}
