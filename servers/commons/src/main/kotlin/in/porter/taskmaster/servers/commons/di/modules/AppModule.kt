package `in`.porter.taskmaster.servers.commons.di.modules

import dagger.Binds
import dagger.Module
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions

import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbImplementation
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbImplementation
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisActions
import `in`.porter.taskmaster.data.RedisRepository.TaskRedisService
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisService
import `in`.porter.taskmaster.data.TaskRepositoryImplementation
import `in`.porter.taskmaster.data.UserRepositoryImplementation
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperService
import `in`.porter.taskmaster.data.mapperclasses.UserMapperActions
import `in`.porter.taskmaster.data.mapperclasses.UserMapperService
import `in`.porter.taskmaster.domain.*


import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindUserService(usersImplementation: UserRepositoryImplementation): UserRepositoryActions

    @Binds
    @Singleton
    fun bindTaskService(tasksImplementation: TaskRepositoryImplementation): TaskRepositoryActions
@Binds
@Singleton
fun bindTaskRedisService(redisImplementation: TaskRedisService): TaskRedisActions

    @Binds
    @Singleton
    fun bindUserRedisService(redisImplementation: UserRedisService): UserRedisActions

    @Binds
    @Singleton
    fun bindUserDbService(userDbImplementation: UserDbImplementation): UserDbActions

    @Binds
    @Singleton
    fun bindTaskDbService(taskDbImplementation: TaskDbImplementation): TaskDbActions

    @Binds
    @Singleton
    fun bindTaskMapperService(taskMapper: TaskMapperService): TaskMapperActions
    @Binds
    @Singleton
    fun bindUserMapperService(userMapper: UserMapperService): UserMapperActions
}


