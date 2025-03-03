package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.DeleteApiUserRequest
import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.usecases.DeleteUserService
import javax.inject.Inject

class DeleteUser @Inject constructor(
    private val DeleteUserService: DeleteUserService,
    private val userMapper: RequestMapper
) {

    suspend fun invoke(apiModel: DeleteApiUserRequest):String {
        val domainTask = userMapper.toDomain(apiModel)
        return DeleteUserService.invoke(domainTask)
    }
}