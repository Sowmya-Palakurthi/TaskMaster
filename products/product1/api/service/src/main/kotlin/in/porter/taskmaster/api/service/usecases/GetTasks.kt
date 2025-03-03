package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.usecases.GetTaskService
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val getTasksService: GetTaskService,
) {
    suspend fun invoke(userId:Int): Result<List<TaskResponse>>{
        return getTasksService.invoke(userId)
    }
}