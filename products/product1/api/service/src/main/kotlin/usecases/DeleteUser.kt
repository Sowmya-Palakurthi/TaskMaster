package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.DeleteApiUserRequest
import `in`.porter.taskmaster.domain.usecases.DeleteUserService
import javax.inject.Inject

class DeleteUser @Inject constructor(
    private val DeleteUserService: DeleteUserService,
    private val userMapper: RequestMapper
) {

    suspend fun invoke(apiModel: DeleteApiUserRequest) {
        val domainTask = userMapper.toDomain(apiModel)
        DeleteUserService.invoke(domainTask)
    }
}