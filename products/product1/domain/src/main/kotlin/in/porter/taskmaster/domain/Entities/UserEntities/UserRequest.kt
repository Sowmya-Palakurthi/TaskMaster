package `in`.porter.taskmaster.domain.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
    var uid: Int,
    var username: String,
    var password: String)
