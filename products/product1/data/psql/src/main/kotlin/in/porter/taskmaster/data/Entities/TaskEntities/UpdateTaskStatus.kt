package `in`.porter.taskmaster.data.Entities.TaskEntities
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskStatusRequest(
    private var userid: Int,
    private var taskid: Int,
    private var isDone: Boolean
) {
    fun getUserId(): Int = userid
    fun getTaskId(): Int = taskid
    fun isDone(): Boolean = isDone
    fun setIsDone(isDone: Boolean) { this.isDone = isDone }
}