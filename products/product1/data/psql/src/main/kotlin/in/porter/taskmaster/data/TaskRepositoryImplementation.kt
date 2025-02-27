package `in`.porter.taskmaster.data




import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.domain.TaskRepositoryActions
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
            cachedTasks
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    override suspend fun addTask(request: TaskRequest): Boolean {
        return try {
            if (request.uid.toString().isNotBlank()) {
                val isAddedToDb = taskDb.addTaskInDb(taskMapper.taskRequestToResponse(request))
                if (isAddedToDb) {
                    redisCache.addTaskData(taskMapper.taskRequestToResponse(request))
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
        }    }

    override suspend fun deleteTask(uid:Int,uuid:Int,password: String): Boolean {
        return try {
            val isValidPassword = userDb.isPasswordValidForUser(uid, password)

            if (isValidPassword) {
                val isDeletedFromDb = taskDb.deleteTaskFromDb(uid,password,uuid)
                redisCache.deleteTaskData(uid,uuid)

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
