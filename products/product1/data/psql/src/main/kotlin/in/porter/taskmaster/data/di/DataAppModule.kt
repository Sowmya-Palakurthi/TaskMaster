package `in`.porter.taskmaster.data.di




import dagger.Binds
import dagger.Module
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


import javax.inject.Singleton

@Module
interface DataAppModule {

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
    fun bindTaskMapperService( taskMapperImpl: TaskMapperService): TaskMapperActions


}


