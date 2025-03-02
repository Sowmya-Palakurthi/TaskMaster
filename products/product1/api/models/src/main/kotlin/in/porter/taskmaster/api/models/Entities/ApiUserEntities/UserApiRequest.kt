package `in`.porter.taskmaster.api.models.Entities.ApiUserEntities

data class UserApiRequest(
    var uid: Int,
    var username: String,
    var password: String
)