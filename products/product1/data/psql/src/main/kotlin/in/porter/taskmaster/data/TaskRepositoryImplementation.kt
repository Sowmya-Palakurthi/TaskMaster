package `in`.porter.taskmaster.data

import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.Entities.TaskEntities.DeleteTaskRequest
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
        }    }

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

//}
//package `in`.porter.taskmaster.data
//
//import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
//import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
//import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
//import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
//import `in`.porter.taskmaster.domain.Entities.TaskEntities.TaskResponse
//import `in`.porter.taskmaster.domain.Entities.TaskEntities.UpdateTaskStatusRequest
//import `in`.porter.taskmaster.domain.TaskRepositoryActions
//import org.apache.logging.log4j.LogManager
//import org.apache.logging.log4j.Logger
//import javax.inject.Inject
//
//class TaskRepositoryImplementation @Inject constructor(
//    private val redisCache: TaskRedisActions,
//    private val taskDb: TaskDbActions,
//    private val userDb: UserDbActions,
//    private val taskMapper: TaskMapperActions
//) : TaskRepositoryActions {
//
//    private val logger: Logger = LogManager.getLogger(TaskRepositoryImplementation::class.java)
//
//    override suspend fun getTasks(uid: Int): List<TaskResponse> {
//        return try {
//            val cachedTasks = redisCache.getTaskData(uid)
//            if (cachedTasks.isNotEmpty()) {
//                cachedTasks
//            } else {
//                val tasksFromDb = taskDb.getTasksFromDb(uid)
//                tasksFromDb.forEach {
//                    redisCache.addTaskData(it)
//                }
//                tasksFromDb
//            }
//        } catch (e: Exception) {
//            logger.error("Error fetching tasks for user ID $uid: ${e.localizedMessage}", e)
//            emptyList()
//        }
//    }
//
//    override suspend fun addTask(request: TaskResponse): Boolean {
//        return try {
//            if (request.uid.toString().isNotBlank()) {
//                val existingTask = taskDb.getTasksFromDb(request.uuid)
//                if (existingTask != null) {
//                    logger.warn("Task with ID ${request.uuid} already exists.")
//                    false
//                } else {
//                    val isAddedToDb = taskDb.addTaskInDb(request)
//                    if (isAddedToDb) {
//                        redisCache.addTaskData(request)
//                        true
//                    } else {
//                        false
//                    }
//                }
//            } else {
//                false
//            }
//        } catch (e: Exception) {
//            logger.error("Error adding task for user ID ${request.uid}: ${e.localizedMessage}", e)
//            false
//        }
//    }
//
//    override suspend fun updateTask(request: UpdateTaskStatusRequest): Boolean {
//        return try {
//            redisCache.deleteTaskData(request.uid, request.uuid)
//            val isUpdatedInDb = taskDb.updateTaskStatus(request)
//            isUpdatedInDb
//        } catch (e: Exception) {
//            logger.error("Error updating task status for task ID ${request.uuid}: ${e.localizedMessage}", e)
//            false
//        }
//    }
//
//    override suspend fun deleteTask(uid: Int, uuid: Int, password: String): Boolean {
//        return try {
//            val isValidPassword = userDb.isPasswordValidForUser(uid, password)
//            if (isValidPassword) {
//                val isDeletedFromDb = taskDb.deleteTaskFromDb(uid, password, uuid)
//                redisCache.deleteTaskData(uid, uuid)
//                isDeletedFromDb
//            } else {
//                false
//            }
//        } catch (e: Exception) {
//            logger.error("Error deleting task for user ID $uid and task ID $uuid: ${e.localizedMessage}", e)
//            false
//        }
//    }
}