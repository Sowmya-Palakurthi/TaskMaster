package `in`.porter.taskmaster.domain.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
    var uid: Int,
    var username: String,
    var password: String)
//) {
//    fun getUserId(): Int = uid
//    fun setUserId(userid: Int) { this.uid = userid }
//
//    fun getUsername(): String = username
//    fun setUsername(username: String) { this.username = username }
//
//    fun getPassword(): String = password
//    fun setPassword(password: String) { this.password = password }
