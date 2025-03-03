package `in`.porter.taskmaster.data.MapperClasses

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.domain.MapperInterfaces.UserMapperActions
import javax.inject.Inject


class UserMapperService @Inject constructor(): UserMapperActions {

        override fun UserRequestToResponse(request: UserRequest): UserResponse {
            return UserResponse(
                uid = request.uid,
                username = request.username
            )
        }

}



