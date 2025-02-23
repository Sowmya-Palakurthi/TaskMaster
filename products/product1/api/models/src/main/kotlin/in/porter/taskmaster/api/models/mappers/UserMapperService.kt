package `in`.porter.taskmaster.api.models.mappers

import `in`.porter.taskmaster.data.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.data.Entities.UserEntities.UserResponse


class UserMapperService {

    fun UserRequestToResponse(request: UserRequest): UserResponse {
        return UserResponse(
            userid=request.getUserId(),
            username=request.getUsername()
        )
    }
}

