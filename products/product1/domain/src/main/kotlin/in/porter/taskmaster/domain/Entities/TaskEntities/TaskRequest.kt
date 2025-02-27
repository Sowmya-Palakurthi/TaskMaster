package `in`.porter.taskmaster.domain.Entities.TaskEntities

import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class TaskRequest(
     val uuid: Int,
     val uid: Int,
     val title: String,
     val description: String,
     val isdone: Boolean,
     val deadline: Long )
//) {
//    fun getUuid(): Int = uuid
//    fun setUuid(uuid: Int) {
//        this.uuid = uuid
//    }
//
//    fun getUid(): Int = uid
//    fun setUid(uid: Int) {
//        this.uid = uid
//    }
//
//    fun getTitle(): String = title
//    fun getDescription(): String = description
//
//    fun getIsDone(): Boolean = isdone
//    fun setIsDone(isdone: Boolean) {
//        this.isdone = isdone
//    }
//
//    fun getDeadline(): Long = deadline
//    fun setDeadline(deadline: Long) {
//        this.deadline = deadline
//    }
//}
