package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject


class DeleteTaskService @Inject constructor(
        private val repository: TaskRepositoryActions
    ) {
        suspend fun invoke(name: DeleteTaskRequest): Boolean = repository.deleteTask(name)
    }
