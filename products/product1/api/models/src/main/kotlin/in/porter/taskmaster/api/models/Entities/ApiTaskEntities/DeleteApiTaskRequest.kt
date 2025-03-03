package `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities

data class DeleteApiTaskRequest(
    val uid:Int,
    val uuid:Int,
    val password:String
)
