package `in`.porter.taskmaster.servers.ktor.di



import dagger.Component
import `in`.porter.taskmaster.api.models.mappers.TaskMapperActions
import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.domain.actions.TaskActions
import `in`.porter.taskmaster.domain.exceptions.UserActions
import `in`.porter.taskmaster.servers.ktor.ConfigActions
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getUserService(): UserActions
    fun getTaskService(): TaskActions
    fun getRedisService(): UserRedisActions
    fun getUserDbService(): UserDbActions
    fun getTaskDbService(): TaskDbActions
   fun getConfigService(): ConfigActions
   fun getTaskMapperService():TaskMapperActions
    companion object {
        fun create(): AppComponent = DaggerAppComponent.create()
    }
}






