package `in`.porter.taskmaster.servers.ktor.di
//
//
//import dagger.Component
//import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
//import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
//import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
//import `in`.porter.taskmaster.data.di.JedisModule
//import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
//import `in`.porter.taskmaster.domain.TaskRepositoryActions
//import `in`.porter.taskmaster.domain.UserRepositoryActions
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [AppModule::class,JedisModule::class,RedisModule::class])
//interface AppComponent {
//    fun getUserService(): UserRepositoryActions
//   fun getTaskService(): TaskRepositoryActions
//    fun getRedisService(): UserRedisActions
//    fun getUserDbService(): UserDbActions
//    fun getTaskDbService(): TaskDbActions
//    fun getTaskMapperService(): TaskMapperActions
//    companion object {
//        fun create(): AppComponent = DaggerAppComponent.create()
//    }
//}
//
//




