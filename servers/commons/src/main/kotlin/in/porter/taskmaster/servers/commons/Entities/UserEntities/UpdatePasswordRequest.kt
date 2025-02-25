package `in`.porter.taskmaster.data.Entities.UserEntities
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordRequest(
     var uid: Int,
     var currentPassword: String,
     var newPassword: String
) {
    fun getUserId(): Int = uid
    fun setUserId(userid: Int) { this.uid = uid }
    fun getCurrentPassword(): String = currentPassword
    fun setCurrentPassword(currentPassword: String) { this.currentPassword = currentPassword }
    fun getNewPassword(): String = newPassword
    fun setNewPassword(newPassword: String) { this.newPassword = newPassword }
}
