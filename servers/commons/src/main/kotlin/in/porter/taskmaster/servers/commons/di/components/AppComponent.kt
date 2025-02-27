package `in`.porter.taskmaster.servers.commons.di.components
////
////
////
////
////import dagger.Component
////import `in`.porter.taskmaster.data.di.JedisModule
////import `in`.porter.taskmaster.data.DatabaseRepository.TaskDbActions
////import `in`.porter.taskmaster.data.DatabaseRepository.UserDbActions
////import `in`.porter.taskmaster.data.RedisRepository.UserRedisActions
////import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
////import `in`.porter.taskmaster.servers.commons.di.modules.AppModule
////
////import javax.inject.Singleton
////
////@Singleton
////@Component(modules = [AppModule::class, JedisModule::class])
////interface AppComponent {
////
////    fun getRedisService(): UserRedisActions
////    fun getUserDbService(): UserDbActions
////    fun getTaskDbService(): TaskDbActions
////    fun getTaskMapperService(): TaskMapperActions
////    companion object {
////        fun create(): AppComponent = DaggerAppComponent.create()
////    }
////}
//
//
//
//
//import dagger.Component
//import `in`.porter.taskmaster.data.di.JedisModule
//import `in`.porter.taskmaster.servers.commons.di.modules.AppModule
//import `in`.porter.taskmaster.servers.commons.di.modules.UtilsModule
//import javax.inject.Singleton
//
//@Singleton
//@Component(
//    dependencies = [RootComponent::class],  // ✅ Depend on RootComponent
//    modules = [
//        AppModule::class,
//        JedisModule::class,
//        UtilsModule::class  // ✅ Include UtilsModule for HttpClient & Environment
//    ]
//)
//interface AppComponent {
//
//    fun getRedisService(): UserRedisActions
//    fun getUserDbService(): UserDbActions
//    fun getTaskDbService(): TaskDbActions
//    fun getTaskMapperService(): TaskMapperActions
//
//    companion object {
//        fun create(rootComponent: RootComponent): AppComponent =
//            DaggerAppComponent.builder()
//                .rootComponent(rootComponent)  // ✅ Pass RootComponent as a dependency
//                .build()
//    }
//}
//
