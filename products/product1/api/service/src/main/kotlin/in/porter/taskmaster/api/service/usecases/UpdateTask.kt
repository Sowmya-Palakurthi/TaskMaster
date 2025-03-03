package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.UpdateApiTaskStatusRequest
import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.usecases.UpdateStatusService
import javax.inject.Inject

class UpdateTask@Inject constructor(
    private val updateTaskService: UpdateStatusService,
    private val taskMapper: RequestMapper
    ) {
    suspend fun invoke(apiModel: UpdateApiTaskStatusRequest) :String{
        val domainTask = taskMapper.toDomain(apiModel)
        return updateTaskService.invoke(domainTask)
    }
}