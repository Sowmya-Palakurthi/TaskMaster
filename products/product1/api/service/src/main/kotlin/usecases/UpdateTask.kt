package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.UpdateApiTaskStatusRequest
import `in`.porter.taskmaster.domain.usecases.UpdateStatusService
import javax.inject.Inject

class UpdateTask@Inject constructor(
    private val updateTaskService: UpdateStatusService,
    private val taskMapper: RequestMapper
    ) {
    suspend fun invoke(apiModel: UpdateApiTaskStatusRequest) {
        val domainTask = taskMapper.toDomain(apiModel)
        updateTaskService.invoke(domainTask)
    }
}