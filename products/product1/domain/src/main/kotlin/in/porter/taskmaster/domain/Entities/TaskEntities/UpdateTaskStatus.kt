package `in`.porter.taskmaster.domain.Entities
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskStatusRequest(
    private var uuid: Int,
    private var uid: Int,
    private var isdone: Boolean
) {
    fun getUserId(): Int = uid
    fun getTaskId(): Int = uuid
    fun isDone(): Boolean = isdone
    fun setIsDone(isDone: Boolean) { this.isdone = isDone }
}