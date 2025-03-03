package `in`.porter.taskmaster.api.models.Entities.ApiUserEntities

import kotlinx.serialization.Serializable

@Serializable
data class UserApiResponse(
    var uid: Int,
    var username: String

)
