package Mappers

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.DeleteApiTaskRequest
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.UpdateApiTaskStatusRequest
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.DeleteApiUserRequest
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UpdateApiPasswordRequest
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.DeleteUserRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UpdatePasswordRequest
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest

class RequestMapper {

fun toDomain(taskApiRequest: TaskApiRequest): TaskRequest {
    return TaskRequest(
        taskApiRequest.uid,
        taskApiRequest.uuid,
        taskApiRequest.title,
        taskApiRequest.description,
        taskApiRequest.isdone,
        taskApiRequest.deadline

    )
}

    fun toDomain(userApiRequest: UserApiRequest): UserRequest {
    return UserRequest(
        userApiRequest.uid,
        userApiRequest.username,
       userApiRequest.password
    )
    }

    fun toDomain(apiRequest: DeleteApiTaskRequest): DeleteTaskRequest {
            return DeleteTaskRequest(
                uid = apiRequest.uid,
                uuid = apiRequest.uuid,
                password = apiRequest.password
            )
        }


    fun toDomain(apiRequest: DeleteApiUserRequest): DeleteUserRequest {
        return DeleteUserRequest(
            uid = apiRequest.uid,
            password = apiRequest.password
        )
    }

    fun toDomain(apiRequest: UpdateApiTaskStatusRequest): UpdateTaskStatusRequest {
        return UpdateTaskStatusRequest(
            uid = apiRequest.uid,
            uuid = apiRequest.uuid,
            isdone=apiRequest.isdone
        )
    }

    fun toDomain(apiRequest:UpdateApiPasswordRequest): UpdatePasswordRequest {
        return UpdatePasswordRequest(
            uid = apiRequest.uid,
            currentPassword = apiRequest.currentPassword,
            newPassword = apiRequest.newPassword

        )
    }



}