package `in`.porter.taskmaster.domain.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
     var uid: Int,
    var username: String)
//) {
//    fun getUserId(): Int = uid
//    fun setUserId(userid: Int) { this.uid = userid }
//    fun getUsername(): String = username
//    fun setUsername(username: String) { this.username = username }
//}