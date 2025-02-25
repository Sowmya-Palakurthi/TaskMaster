package `in`.porter.taskmaster.data.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    private var uid: Int,
    private var username: String
) {
    fun getUserId(): Int = uid
    fun setUserId(userid: Int) { this.uid = userid }
    fun getUsername(): String = username
    fun setUsername(username: String) { this.username = username }
}