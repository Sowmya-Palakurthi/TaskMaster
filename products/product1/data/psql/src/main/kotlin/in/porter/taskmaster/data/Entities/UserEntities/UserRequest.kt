package `in`.porter.taskmaster.data.Entities.UserEntities
import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
    var uid: Int,
    var emailid: String,
    var username: String,
    var password: String
) {
    fun getUserId(): Int = uid
    fun setUserId(userid: Int) { this.uid = userid }

    fun getEmailId(): String = emailid
    fun setEmailId(emailid: String) { this.emailid = emailid }

    fun getUsername(): String = username
    fun setUsername(username: String) { this.username = username }

    fun getPassword(): String = password
    fun setPassword(password: String) { this.password = password }
}