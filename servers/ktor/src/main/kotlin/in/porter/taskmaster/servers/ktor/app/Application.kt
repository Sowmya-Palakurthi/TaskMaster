//package `in`.porter.taskmaster.servers.ktor.app
//
//import `in`.porter.taskmaster.servers.commons.di.factories.DatabaseFactory
//import `in`.porter.taskmaster.servers.ktor.app.MyApplication.Companion.appComponent
//import `in`.porter.taskmaster.servers.ktor.di.AppComponent
//import io.ktor.server.application.Application
//
//
//
//
//
//
//import io.ktor.server.application.*
//import io.ktor.server.engine.*
//import io.ktor.server.netty.*
//import io.ktor.server.plugins.contentnegotiation.*
//import java.security.Security
//
//class MyApplication {
//    companion object {
//        lateinit var appComponent: AppComponent
//    }}
//}
//fun main() {
//
//    MyApplication.appComponent = AppComponent.create()
//    val configLoader=appComponent.getConfigService()
//    embeddedServer(Netty, port = configLoader.getServerPort(), host = configLoader.getServerHost()) {
//        module()
//    }.start(wait = true)
//}
//
//fun Application.module() {
//    val taskService = appComponent.getTaskService()
//    val userService = appComponent.getUserService()
//    val configService=appComponent.getConfigService()
//
//
//    DatabaseFactory.init()
//
////    install(ContentNegotiation) {
////        json()
////    }
//}