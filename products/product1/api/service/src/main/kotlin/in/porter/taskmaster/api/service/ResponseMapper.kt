package `in`.porter.taskmaster.api.service

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiResponse
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse

class ResponseMapper {

    fun toDomain(taskApiResponse: TaskApiResponse): TaskResponse {
        return TaskResponse(
            taskApiResponse.uid,
            taskApiResponse.uuid,
            taskApiResponse.title,
            taskApiResponse.description,
            taskApiResponse.isdone,
            taskApiResponse.deadline
        )
    }

    fun toDomain(userApiResponse: UserApiResponse): UserResponse {
        return UserResponse(
            userApiResponse.uid,
            userApiResponse.username
        )
    }


}