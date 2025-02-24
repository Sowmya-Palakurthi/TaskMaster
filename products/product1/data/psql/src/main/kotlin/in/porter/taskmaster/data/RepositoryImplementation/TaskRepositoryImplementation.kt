package `in`.porter.taskmaster.data.RepositoryImplementation

import `in`.porter.taskmaster.api.models.mappers.TaskMapperActions
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import javax.inject.Inject
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.Entities.TaskEntities.TaskRequest
import `in`.porter.taskmaster.data.Entities.TaskEntities.UpdateTaskStatusRequest
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.domain.Entities.TaskRequest
import `in`.porter.taskmaster.domain.actions.TaskActions

class TaskRepositoryImplementation @Inject constructor(
    private val redisCache: TaskRedisActions,
    private val taskDb: TaskDbActions,
    private val userDb: UserDbActions,
    private val taskMapper:TaskMapperActions
) : TaskActions {

    override suspend fun getTasks(userid: Int): List<TaskRequest> {

        return try {
            val cachedTasks = redisCache.getTaskData(userid)
            if (cachedTasks != null) {
                cachedTasks
            } else {
                val tasksList = taskDb.getTasksFromDb(userid)
                if (tasksList.isNotEmpty()) {
                    tasksList.forEach { task -> redisCache.addTaskData(task) }
                }
                tasksList
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    override suspend fun addTask(request: TaskRequest): Boolean {
        return try {
            if (request.getUserId().toString().isNotBlank()) {
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



    override suspend fun updateTaskStatus(request: UpdateTaskStatusRequest): Boolean {
        return try {
            redisCache.deleteTaskData(request.getUserId(), request.getTaskId())
            val isUpdatedInDb = taskDb.updateTaskStatus(request)
            isUpdatedInDb
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }    }

    override suspend fun deleteTask(userid:Int,taskid:Int,password: String): Boolean {
        return try {
            val isValidPassword = userDb.isPasswordValidForUser(userid, password)

            if (isValidPassword) {
                val isDeletedFromDb = taskDb.deleteTaskFromDb(userid,password,taskid)
                redisCache.deleteTaskData(userid,taskid)

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
