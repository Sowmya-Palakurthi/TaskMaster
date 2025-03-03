package `in`.porter.taskmaster.domain.Entities.TaskEntities
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskStatusRequest(
    var uid: Int,
    var uuid: Int,
    var isdone: Boolean

    )
