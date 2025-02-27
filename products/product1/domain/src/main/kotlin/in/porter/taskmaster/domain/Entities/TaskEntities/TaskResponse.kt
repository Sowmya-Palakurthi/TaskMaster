package `in`.porter.taskmaster.domain.Entities.TaskEntities

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDate

@Serializable
data class TaskResponse(
     var uuid: Int,
     var uid: Int,
     var title: String,
     var description: String,
     var isdone: Boolean,
    @Contextual
     var deadline: LocalDate)
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
//    fun getDeadline(): LocalDate = deadline
//    fun setDeadline(deadline: LocalDate) {
//        this.deadline = deadline
//    }
//}


