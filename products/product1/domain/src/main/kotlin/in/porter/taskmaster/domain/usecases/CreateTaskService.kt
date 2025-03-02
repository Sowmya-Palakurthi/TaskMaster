package `in`.porter.taskmaster.domain.usecases

import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import javax.inject.Inject

class CreateTaskService @Inject constructor(private val taskRepository: TaskRepositoryActions, private val taskMapper: TaskMapperActions) {

    suspend fun invoke(request: TaskRequest)  = taskRepository.addTask(taskMapper.taskRequestToResponse(request))

}