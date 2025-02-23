package `in`.porter.taskmaster.servers.ktor.di

import dagger.Binds
import dagger.Module
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbImplementation
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisService
import `in`.porter.taskmaster.domain.Repository.UserActions
import `in`.porter.taskmaster.domain.Repository.UserService
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindUserService(usersImplementation: UserService): UserActions
//
//    @Binds
//    @Singleton
//    fun bindTaskService(tasksImplementation: TasksImplemetation): TaskActions.kt

    @Binds
    @Singleton
    fun bindRedisService(redisImplementation: UserRedisService): UserRedisActions

    @Binds
    @Singleton
    fun bindUserDbService(userDbImplementation: UserDbImplementation): UserDbActions
/*
    @Binds
    @Singleton
    fun bindTaskDbService(taskDbImplementation: TaskDbImplementation): TaskDbActions

    @Binds
    @Singleton
    fun bindConfigService(configLoader: ConfigLoader): ConfigActions


    @Binds
    @Singleton
    fun bindMapperService(taskMapper: TaskMapper ): TaskMapperInterface
}

@Module
object SecurityModule {
    @Provides
    @Singleton
    fun provideSecurity(config: ConfigActions): Security {
        return Security(config)
    }

}
*/}
