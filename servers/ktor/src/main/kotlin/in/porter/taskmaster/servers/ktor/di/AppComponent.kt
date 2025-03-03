
package `in`.porter.taskmaster.servers.ktor.di

//import dagger.Component
//import `in`.porter.taskmaster.servers.ktor.Handlers.TaskHandler
//import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [TaskModule::class])
//interface AppComponent {
//    fun taskHandler(): TaskHandler
//  fun userHandler(): UserHandler
//    companion object {
//        fun create(): AppComponent = DaggerAppComponent.create()
//    }
//}



import dagger.Component
import `in`.porter.taskmaster.servers.ktor.Handlers.TaskHandler
import `in`.porter.taskmaster.servers.ktor.Handlers.UserHandler
import javax.inject.Singleton

@Singleton
@Component(modules = [TaskModule::class])
interface AppComponent {
    fun taskHandler(): TaskHandler
    fun userHandler(): UserHandler

    companion object {
        fun create(): AppComponent = DaggerAppComponent.create()
    }
}