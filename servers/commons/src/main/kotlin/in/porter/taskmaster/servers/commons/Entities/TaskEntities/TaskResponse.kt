

package `in`.porter.taskmaster.servers.commons.Entities.TaskEntities

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class TaskResponse(
    private var uuid: Int,
    private var uid: Int,
    private var title: String,
    private var description: String,
    private var isdone: Boolean,
    @Contextual private var deadline: Instant
) {
    fun getTaskId(): Int = uuid
    fun setTaskId(uuid: Int) {
        this.uuid = uuid
    }

    fun getUserId(): Int = uid
    fun setUserId(userid: Int) {
        this.uid = userid
    }

    fun getTitle(): String = title
    fun getDescription(): String = description

    fun getIsDone(): Boolean = isdone
    fun setIsDone(isdone: Boolean) {
        this.isdone = isdone
    }

    fun getDeadline(): Instant = deadline
    fun setDeadline(deadline: Instant) {
        this.deadline = deadline
    }
}
