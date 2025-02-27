package `in`.porter.taskmaster.data.mapperclasses

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

interface UserMapperActions  {


    fun UserRequestToResponse(request: UserRequest): UserResponse

}