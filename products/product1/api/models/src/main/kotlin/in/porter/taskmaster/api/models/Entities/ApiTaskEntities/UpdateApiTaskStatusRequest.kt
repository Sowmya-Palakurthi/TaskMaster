package `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities

data class UpdateApiTaskStatusRequest(
    val uuid: Int,
    val uid: Int,
    val isdone: Boolean
)
