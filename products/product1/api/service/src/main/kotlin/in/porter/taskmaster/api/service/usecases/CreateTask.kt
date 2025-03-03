package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.domain.usecases.CreateTaskService
import javax.inject.Inject

class CreateTask @Inject constructor(

private val createTaskService: CreateTaskService,
private val taskMapper: RequestMapper
) {
    suspend fun invoke(apiModel: TaskApiRequest):String {
        val domainTask = taskMapper.toDomain(apiModel)
      return createTaskService.invoke(domainTask)

    }
}