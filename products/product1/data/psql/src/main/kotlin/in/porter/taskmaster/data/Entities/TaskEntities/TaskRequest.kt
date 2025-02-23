package `in`.porter.taskmaster.data.Entities.TaskEntities

import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class TaskRequest(
    private var taskid: Int,
    private var userid: Int,
    private var title: String,
    private var description: String,
    private var isdone: Boolean,
    private var deadline: Long
) {
    fun getTaskId(): Int = taskid
    fun setTaskId(taskid:Int){
        this.taskid=taskid
    }
    fun getUserId(): Int = userid
    fun setUserId(taskid:Int){
        this.taskid=taskid
    }
    fun getTitle(): String = title
    fun getDescription(): String = description
    fun getIsDone(): Boolean = isdone
    fun setIsDone(isdone: Boolean) {
        this.isdone = isdone
    }
    fun getDeadline(): Long = deadline
    fun setDeadline(deadline:Long) {
        this.deadline = deadline
    }
}
