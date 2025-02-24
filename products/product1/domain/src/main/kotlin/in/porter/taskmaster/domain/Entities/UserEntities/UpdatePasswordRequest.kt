package `in`.porter.taskmaster.domain.Entities.UserEntities
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordRequest(
     var uid: Int,
     var currentPassword: String,
     var newPassword: String) {
    fun getUserId(): Int = uid
    fun setUserId(uid: Int) {
        this.uid = uid
    }
}
