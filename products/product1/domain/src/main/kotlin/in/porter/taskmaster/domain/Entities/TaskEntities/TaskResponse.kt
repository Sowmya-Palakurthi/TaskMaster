package `in`.porter.taskmaster.domain.Entities.TaskEntities






import `in`.porter.taskmaster.domain.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class TaskResponse(
    var uuid: Int,
    var uid: Int,
    var title: String,
    var description: String,
    var isdone: Boolean,
    @Serializable(with = LocalDateSerializer::class)
    var deadline: LocalDate
)