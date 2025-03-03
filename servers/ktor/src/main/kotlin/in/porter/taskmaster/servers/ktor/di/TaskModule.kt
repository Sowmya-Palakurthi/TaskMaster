package `in`.porter.taskmaster.servers.ktor.di

import dagger.Module
import dagger.Provides
import `in`.porter.taskmaster.api.service.usecases.*
import `in`.porter.taskmaster.data.CommonRepository.TaskRepositoryImplementation
import `in`.porter.taskmaster.data.CommonRepository.UserRepositoryImplementation
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbImplementation
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbImplementation
import `in`.porter.taskmaster.data.MapperClasses.TaskMapperService
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisService
import `in`.porter.taskmaster.data.RedisRepository.UserRedisService
import `in`.porter.taskmaster.domain.DbInterfaces.TaskDbActions
import `in`.porter.taskmaster.domain.DbInterfaces.UserDbActions
import `in`.porter.taskmaster.domain.MapperInterfaces.TaskMapperActions
import `in`.porter.taskmaster.domain.RedisInterfaces.TaskRedisActions
import `in`.porter.taskmaster.domain.RedisInterfaces.UserRedisActions
import `in`.porter.taskmaster.domain.RepositoryActions.TaskRepositoryActions
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import `in`.porter.taskmaster.servers.ktor.Handlers.TaskHandler
import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
import redis.clients.jedis.Jedis
import javax.inject.Singleton

@Module
object TaskModule {

    @Provides
    @Singleton
    fun provideTaskHandler(
        createTask: CreateTask,
        deleteTask: DeleteTask,
        updateTask: UpdateTask,
        getTasks: GetTasks
    ): TaskHandler {
        return TaskHandler(createTask, deleteTask, updateTask, getTasks)
    }

    @Provides
    @Singleton
    fun provideUserHandler(
        createUser: CreateUser,
        deleteUser: DeleteUser,
        updateUser: UpdateUser,
        getUsers: GetUser
    ): UserHandler {
        return UserHandler(createUser, deleteUser, updateUser, getUsers)
    }

    @Provides
    @Singleton
    fun provideTaskMapper(): TaskMapperActions {
        return TaskMapperService()
    }


    @Provides
    @Singleton
    fun provideTaskRedisActions(jedis: Jedis): TaskRedisActions {
        return TaskRedisService(jedis)
    }

    @Provides
    @Singleton
    fun provideUserRedisActions(jedis: Jedis): UserRedisActions {
        return UserRedisService(jedis)
    }
    @Provides
    @Singleton
    fun provideTaskDbActions(): TaskDbActions {
        return TaskDbImplementation(provideTaskMapper())
    }
    @Provides
    @Singleton
    fun provideJedis(): Jedis {
        // Configure the Jedis instance as needed
        return Jedis("localhost", 6379)
    }
    @Provides
    @Singleton
    fun provideUserDbActions(): UserDbActions {
        return UserDbImplementation()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDbActions: UserDbActions
    ): UserRepositoryActions {
        return UserRepositoryImplementation(provideUserRedisActions(provideJedis()), userDbActions)
    }
    @Provides
    @Singleton
    fun provideTaskRepository(
        taskRedisActions: TaskRedisActions,
        taskDbActions: TaskDbActions,
        userDbActions: UserDbActions,
        taskMapperActions: TaskMapperActions
    ): TaskRepositoryActions {
        return TaskRepositoryImplementation(taskRedisActions, taskDbActions, userDbActions, taskMapperActions)
    }
}
