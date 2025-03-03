package `in`.porter.taskmaster.domain.Entities.TaskEntities

import kotlinx.serialization.Serializable



@Serializable
data class TaskRequest(
     val uuid: Int,
     val uid: Int,
     val title: String,
     val description: String,
     val isdone: Boolean,
     val deadline: Long )
