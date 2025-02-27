package `in`.porter.taskmaster.domain.Entities.TaskEntities
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTaskStatusRequest(
    var uid: Int,
    var uuid: Int,
    var isdone: Boolean)
//) {
//    fun getUserId(): Int = userid
//    fun getTaskId(): Int = taskid
//    fun isDone(): Boolean = isDone
//    fun setIsDone(isDone: Boolean) { this.isDone = isDone }
//}