package `in`.porter.taskmaster.servers.ktor.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import `in`.porter.taskmaster.api.models.mappers.TaskMapperActions
import `in`.porter.taskmaster.api.models.mappers.TaskMapperService
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbImplementation
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbImplementation
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisService
import `in`.porter.taskmaster.data.RepositoryImplementation.TaskRepositoryImplementation
import `in`.porter.taskmaster.data.RepositoryImplementation.UserRepositoryImplementation
import `in`.porter.taskmaster.domain.exceptions.UserActions
import `in`.porter.taskmaster.domain.actions.TaskActions
import `in`.porter.taskmaster.servers.ktor.ConfigActions
import `in`.porter.taskmaster.servers.ktor.ConfigService
import io.ktor.server.config.*
import java.security.Security
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindUserService(usersImplementation: UserRepositoryImplementation): UserActions

    @Binds
    @Singleton
    fun bindTaskService(tasksImplementation: TaskRepositoryImplementation): TaskActions

    @Binds
    @Singleton
    fun bindRedisService(redisImplementation: UserRedisService): UserRedisActions

    @Binds
    @Singleton
    fun bindUserDbService(userDbImplementation: UserDbImplementation): UserDbActions

    @Binds
    @Singleton
    fun bindTaskDbService(taskDbImplementation: TaskDbImplementation): TaskDbActions

    @Binds
    @Singleton
    fun bindConfigService(configLoader: ConfigService): ConfigActions


    @Binds
    @Singleton
    fun bindMapperService(taskMapper: TaskMapperActions ): TaskMapperService
}


