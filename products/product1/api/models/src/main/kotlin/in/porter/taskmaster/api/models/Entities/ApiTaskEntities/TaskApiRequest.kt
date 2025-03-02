package `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities

data class TaskApiRequest(


    val uuid: Int,
    val uid: Int,
    val title: String,
    val description: String,
    val isdone: Boolean,
    val deadline: Long
)
