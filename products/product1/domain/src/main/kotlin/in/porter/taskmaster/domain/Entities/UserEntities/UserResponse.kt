package `in`.porter.taskmaster.domain.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
     var uid: Int,
    var username: String)
