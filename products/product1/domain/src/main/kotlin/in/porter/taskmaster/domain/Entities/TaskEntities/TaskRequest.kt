package `in`.porter.taskmaster.domain.Entities
//package 'in'.porter.taskmaster.data.Entities.TaskEntities

import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class TaskRequest(
    private var uuid: Int,
    private var uid: Int,
    private var title: String,
    private var description: String,
    private var isdone: Boolean,
    private var deadline: Long) {
    fun getTaskId(): Int = uuid
    fun setTaskId(uuid: Int) {
        this.uuid = uuid
    }

    fun getUserId(): Int = uuid
    fun setUserId(uuid: Int) {
        this.uuid = uuid
    }

    fun getTitle(): String = title
    fun getDescription(): String = description
    fun getIsDone(): Boolean = isdone

}
