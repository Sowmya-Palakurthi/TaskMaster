//package `in`.porter.taskmaster.servers.ktor.app
//
//import `in`.porter.taskmaster.servers.ktor.di.HttpComponentFactory
//import io.ktor.server.engine.*
//import io.ktor.server.netty.*
//import io.ktor.util.*
//import org.apache.logging.log4j.kotlin.logger
//import java.time.Duration
//
//private val logger = logger("in.porter.taskmaster.servers.ktor.app.MainRunnerKt")
//
//@KtorExperimentalAPI
//suspend fun main() {
//  HttpComponentFactory.build().run.invoke(
//    initApplication = { initApplication() },
//    startApplication = { server -> server.start(false) },
//    stopApplication = { server -> shutdownServer(server) }
//  )
//}
//
//private fun initApplication(): NettyApplicationEngine =
//  embeddedServer(Netty, port = 8080) { main() }
//
//private fun shutdownServer(server: NettyApplicationEngine) {
//  logger.info { "Shutting down server" }
//  try {
//    server.stop(Duration.ofSeconds(5).toMillis(), Duration.ofSeconds(15).toMillis())
//  } catch (e: Exception) {
//    logger.error(e) { "Server shut down failed" }
//  }
//  logger.info { "Server shut down complete" }
//}
package `in`.porter.taskmaster.servers.ktor.app

import `in`.porter.taskmaster.servers.commons.di.factories.DatabaseFactory
import `in`.porter.taskmaster.servers.ktor.app.MyApplication.Companion.appComponent
import `in`.porter.taskmaster.servers.ktor.app.MyApplication.appComponent
import `in`.porter.taskmaster.servers.ktor.di.AppComponent
import io.ktor.server.application.Application






import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import java.security.Security
object MyApplication {
  lateinit var appComponent: AppComponent
}


fun main() {

  MyApplication.appComponent = AppComponent.create()
  val configLoader=appComponent.getConfigService()
  embeddedServer(Netty, port = configLoader.getServerPort(), host = configLoader.getServerHost()) {
    module()
  }.start(wait = true)
}

fun Application.module() {
  val taskService = appComponent.getTaskService()
  val userService = appComponent.getUserService()
  val configService=appComponent.getConfigService()


  DatabaseFactory.init()

//    install(ContentNegotiation) {
//        json()
//    }
}