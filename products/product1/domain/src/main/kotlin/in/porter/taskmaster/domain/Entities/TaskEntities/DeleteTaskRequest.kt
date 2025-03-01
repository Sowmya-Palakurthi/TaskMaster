package `in`.porter.taskmaster.domain.Entities.TaskEntities

data class DeleteTaskRequest(
    val uid:Int,
    val uuid:Int,
    val password:String
)
