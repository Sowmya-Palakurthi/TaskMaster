package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.domain.usecases.CreateTaskService
import javax.inject.Inject

class CreateTask @Inject constructor(

private val createTaskService: CreateTaskService,
private val taskMapper: RequestMapper
) {
    suspend fun invoke(apiModel: TaskApiRequest) {
        val domainTask = taskMapper.toDomain(apiModel)
        createTaskService.invoke(domainTask)
    }
}