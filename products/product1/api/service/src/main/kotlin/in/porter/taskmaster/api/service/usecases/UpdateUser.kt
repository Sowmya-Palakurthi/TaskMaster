package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UpdateApiPasswordRequest
import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.usecases.UpdatePasswordService
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val updateUserService: UpdatePasswordService,
    private val userMapper: RequestMapper

) {

    suspend fun invoke(apiModel: UpdateApiPasswordRequest):String {
        val domainTask = userMapper.toDomain(apiModel)
        return updateUserService.invoke(domainTask)
    }


}