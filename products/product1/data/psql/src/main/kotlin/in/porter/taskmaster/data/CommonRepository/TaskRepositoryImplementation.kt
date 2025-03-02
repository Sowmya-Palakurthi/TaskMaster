package `in`.porter.taskmaster.data.CommonRepository

import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import javax.inject.Inject

class TaskRepositoryImplementation @Inject constructor(
    private val redisCache: TaskRedisActions,
    private val taskDb: TaskDbActions,
    private val userDb: UserDbActions,
    private val taskMapper: TaskMapperActions
) : TaskRepositoryActions {

    override suspend fun getTasks(uid: Int): List<TaskResponse> {
        return try {
            val cachedTasks = redisCache.getTaskData(uid)
            if (cachedTasks.isNotEmpty()) {
                cachedTasks
            } else {
                val tasksFromDb = taskDb.getTasksFromDb(uid)
                tasksFromDb.forEach {
                    redisCache.addTaskData(it)
                }
                tasksFromDb
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    override suspend fun addTask(request: TaskResponse): Boolean {
        return try {
            if (request.uid.toString().isNotBlank()) {
                val isAddedToDb = taskDb.addTaskInDb(request)
                if (isAddedToDb) {
                    redisCache.addTaskData(request)
                    true
                } else {
                    false
                }
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updateTask(request: UpdateTaskStatusRequest): Boolean {
        return try {

            redisCache.deleteTaskData(request.uid, request.uuid)
            val isUpdatedInDb = taskDb.updateTaskStatus(request)
            isUpdatedInDb
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun deleteTask(deleteRequest: DeleteTaskRequest): Boolean {
        return try {
            val isValidPassword = userDb.isPasswordValidForUser(deleteRequest.uid, deleteRequest.password)

            if (isValidPassword) {
                val isDeletedFromDb = taskDb.deleteTaskFromDb(deleteRequest)

                redisCache.deleteTaskData(deleteRequest.uid,deleteRequest.uuid)

                isDeletedFromDb
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }
}