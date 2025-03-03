package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.DeleteApiTaskRequest
import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.usecases.DeleteTaskService
import javax.inject.Inject

class DeleteTask @Inject constructor(

    private val DeleteTaskService: DeleteTaskService,
    private val taskMapper: RequestMapper
) {
    suspend fun invoke(apiModel:DeleteApiTaskRequest):String {
        val domainTask = taskMapper.toDomain(apiModel)
       return DeleteTaskService.invoke(domainTask)
    }
}