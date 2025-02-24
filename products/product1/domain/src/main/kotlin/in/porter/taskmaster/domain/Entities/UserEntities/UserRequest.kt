package `in`.porter.taskmaster.domain.Entities
import kotlinx.serialization.Serializable


@Serializable
data class UserRequest(
    var uid: Int,
    var username: String,
    var password: String)
 {
    fun getUserId(): Int = uid
    fun setUserId(userid: Int) { this.uid = userid }


}