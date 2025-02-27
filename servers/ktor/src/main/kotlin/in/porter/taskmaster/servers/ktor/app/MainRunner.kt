package `in`.porter.taskmaster.servers.ktor.app
////
////import `in`.porter.taskmaster.servers.ktor.di.HttpComponentFactory
////import io.ktor.server.engine.*
////import io.ktor.server.netty.*
////import io.ktor.util.*
////import org.apache.logging.log4j.kotlin.logger
////import java.time.Duration
////
////private val logger = logger("in.porter.taskmaster.servers.ktor.app.MainRunnerKt")
////
////@KtorExperimentalAPI
////suspend fun main() {
////  HttpComponentFactory.build().run.invoke(
////    initApplication = { initApplication() },
////    startApplication = { server -> server.start(false) },
////    stopApplication = { server -> shutdownServer(server) }
////  )
////}
////
////private fun initApplication(): NettyApplicationEngine =
////  embeddedServer(Netty, port = 8080) { main() }
////
////private fun shutdownServer(server: NettyApplicationEngine) {
////  logger.info { "Shutting down server" }
////  try {
////    server.stop(Duration.ofSeconds(5).toMillis(), Duration.ofSeconds(15).toMillis())
////  } catch (e: Exception) {
////    logger.error(e) { "Server shut down failed" }
////  }
////  logger.info { "Server shut down complete" }
////}
//
//
//package `in`.porter.taskmaster.servers.ktor.app
//import `in`.porter.taskmaster.servers.ktor.Routes.configureRouting
//import `in`.porter.taskmaster.servers.ktor.di.HttpComponent
//import `in`.porter.taskmaster.servers.ktor.di.HttpComponentFactory
//import io.ktor.server.application.*
//import io.ktor.server.engine.*
//import io.ktor.server.netty.*
//import io.ktor.server.plugins.contentnegotiation.*
//import io.ktor.serialization.kotlinx.json.*
//
//import java.time.Duration
//import org.apache.logging.log4j.kotlin.logger
//
//private val logger = logger("in.porter.taskmaster.servers.ktor.app.MainRunnerKt")
//
//
//suspend fun main() {
//  HttpComponentFactory.build().run.invoke(
//    initApplication = { initApplication() },
//    startApplication = { server -> server.start(wait = true) },
//    stopApplication = { server -> shutdownServer(server) }
//  )
//}
//
//private fun initApplication(): NettyApplicationEngine {
//  // Initialize Dependency Injection
//  MyApplication.appComponent = HttpComponent.create()
//
//  return embeddedServer(
//    Netty,
//    port = 8080
//
//  ) {
//    module()
//  }
//}
//
//fun Application.module() {
//  val taskService = MyApplication.appComponent.getTaskService()
//  val userService = MyApplication.appComponent.getUserService()
//
//  // Initialize Database
//  DatabaseFactory.init()
//
//  val taskMapper =  MyApplication.appComponent.getTaskMapperService()
//  configureRouting(userService, taskService, taskMapper)
//
//  install(ContentNegotiation) {
//    json()
//  }
//}
//
//private fun shutdownServer(server: NettyApplicationEngine) {
//  logger.info { "Shutting down server" }
//  try {
//    server.stop(Duration.ofSeconds(5).toMillis(), Duration.ofSeconds(15).toMillis())
//  } catch (e: Exception) {
//    logger.error(e) { "Server shutdown failed" }
//  }
//  logger.info { "Server shutdown complete" }
//}

import `in`.porter.taskmaster.servers.ktor.di.HttpComponentFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import org.apache.logging.log4j.kotlin.logger
import java.time.Duration

private val logger = logger("in.porter.kptr.servers.ktor.app.MainRunnerKt")

@KtorExperimentalAPI
suspend fun main() {
  HttpComponentFactory.build().run.invoke(
    initApplication = { initApplication() },
    startApplication = { server -> server.start(false) },
    stopApplication = { server -> shutdownServer(server) }
  )
}

private fun initApplication(): NettyApplicationEngine =
  embeddedServer(Netty, port = 8080) { main() }

private fun shutdownServer(server: NettyApplicationEngine) {
  logger.info { "Shutting down server" }
  try {
    server.stop(Duration.ofSeconds(5).toMillis(), Duration.ofSeconds(15).toMillis())
  } catch (e: Exception) {
    logger.error(e) { "Server shut down failed" }
  }
  logger.info { "Server shut down complete" }
}
