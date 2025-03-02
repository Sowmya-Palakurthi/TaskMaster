package Mappers
//
//import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
//import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiResponse
//import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiRequest
//import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiResponse
//import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
//import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
//import `in`.porter.taskmaster.domain.Entities.UserEntities.UserRequest
//import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
//
//class ResponseMapper {
//
//
//    fun toDomain(taskApiResponse: TaskApiResponse): TaskResponse {
//        return TaskResponse(
//            taskApiResponse.uid,
//            taskApiResponse.uuid,
//            taskApiResponse.title,
//            taskApiResponse.description,
//            taskApiResponse.isdone,
//            taskApiResponse.deadline
//
//        )
//    }
//
//    fun toApi(taskResponse: TaskResponse): TaskApiResponse {
//        return TaskApiResponse(
//            taskResponse.uid,
//            taskResponse.uuid,
//            taskResponse.title,
//            taskResponse.description,
//            taskResponse.isdone,
//            taskResponse.deadline
//
//        )
//    }
//
//    fun toDomain(userApiResponse: UserApiResponse): UserResponse {
//        return UserResponse(
//            userApiResponse.uid,
//            userApiResponse.username
//
//        )
//    }
//    fun toApi(userResponse: UserResponse): UserApiResponse {
//        return UserApiResponse(
//            userResponse.uid,
//            userResponse.username,
//
//
//        )
//    }
//
//
//
//
//}

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiResponse
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import java.time.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate

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