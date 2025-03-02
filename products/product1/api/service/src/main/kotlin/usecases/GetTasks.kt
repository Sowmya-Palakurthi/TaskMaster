package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiTaskEntities.TaskApiRequest
import `in`.porter.taskmaster.domain.usecases.GetTaskService
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val getTasksService: GetTaskService,
    private val userMapper: RequestMapper
) {
    suspend fun invoke(TaskApiRequest: TaskApiRequest) {
        val domainTask = userMapper.toDomain(TaskApiRequest)
        getTasksService.invoke(domainTask)
    }
}