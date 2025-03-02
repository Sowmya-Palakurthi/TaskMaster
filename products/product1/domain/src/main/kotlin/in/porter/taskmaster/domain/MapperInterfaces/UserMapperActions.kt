package `in`.porter.taskmaster.domain.MapperInterfaces

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserMapperActions  {


    fun UserRequestToResponse(request: UserRequest): UserResponse

}