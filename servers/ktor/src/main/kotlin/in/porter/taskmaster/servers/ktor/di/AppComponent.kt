package `in`.porter.taskmaster.servers.ktor.di



import dagger.Component
import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
import `in`.porter.taskmaster.domain.Repository.UserActions
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getUserService(): UserActions
   // fun getTaskService(): TaskRepository
    fun getRedisService(): UserRedisActions
    fun getUserDbService(): UserDbActions
    //fun getTaskDbService(): TaskDbActions
   // fun getConfigService(): ConfigActions
    //fun getTaskMapperService():TaskMapperInterface
    companion object {
        fun create(): AppComponent = DaggerAppComponent.create()
    }
}






