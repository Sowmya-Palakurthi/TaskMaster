package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.UpdateApiTaskStatusRequest
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UpdateApiPasswordRequest
import `in`.porter.taskmaster.domain.usecases.UpdatePasswordService
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val updateUserService: UpdatePasswordService,
    private val userMapper: RequestMapper

) {

    suspend fun invoke(apiModel: UpdateApiPasswordRequest) {
        val domainTask = userMapper.toDomain(apiModel)
        updateUserService.invoke(domainTask)
    }


}