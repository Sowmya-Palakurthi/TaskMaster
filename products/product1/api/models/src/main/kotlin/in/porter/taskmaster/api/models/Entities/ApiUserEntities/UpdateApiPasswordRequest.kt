package `in`.porter.taskmaster.api.models.Entities.ApiUserEntities

data class UpdateApiPasswordRequest(

    var uid: Int,
    var currentPassword: String,
    var newPassword: String

)
