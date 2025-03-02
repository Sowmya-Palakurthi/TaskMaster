package `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities



import `in`.porter.taskmaster.api.models.async.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class TaskApiResponse(
    var uuid: Int,
    var uid: Int,
    var title: String,
    var description: String,
    var isdone: Boolean,
    @Serializable(with = LocalDateSerializer::class)
    var deadline: LocalDate
)