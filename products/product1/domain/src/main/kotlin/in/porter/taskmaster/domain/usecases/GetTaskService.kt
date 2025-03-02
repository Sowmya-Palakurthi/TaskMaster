package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject

class GetTaskService @Inject constructor(
        private val repository: TaskRepositoryActions
    ) {
        suspend fun invoke(request: TaskRequest): List<TaskResponse> = repository.getTasks(request.uid)
}