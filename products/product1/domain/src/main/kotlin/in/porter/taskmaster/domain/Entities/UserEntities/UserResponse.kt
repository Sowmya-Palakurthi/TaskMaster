package `in`.porter.taskmaster.domain.Entities
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    private var uid: Int,
    private var username: String)
 {
    fun getUserId(): Int = uid
    fun setUserId(uid: Int) { this.uid = uid }
    fun getUsername(): String = username
    fun setUsername(username: String) { this.username = username }
}