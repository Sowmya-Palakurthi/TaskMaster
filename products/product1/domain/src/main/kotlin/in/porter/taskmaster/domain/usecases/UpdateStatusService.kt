package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject

class UpdateStatusService @Inject constructor(
        private val repository: TaskRepositoryActions
    ) {
        suspend fun invoke(request: UpdateTaskStatusRequest): Boolean = repository.updateTask(request)
}