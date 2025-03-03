package `in`.porter.taskmaster.client.config

import io.ktor.http.*

data class ClientConfig(
  val protocol: URLProtocol,
  val host: String,
  val port: Int? = null
) {

  companion object {

    val Staging = ClientConfig(
      protocol = URLProtocol.HTTPS,
      host = "taskmaster-staging-ktor.porter.in"
    )

    val Production = ClientConfig(
      protocol = URLProtocol.HTTP,
      host = "taskmaster-prod-ktor.internal.porter.in"
    )
    val Redis=ClientConfig(
      protocol = URLProtocol.HTTP,
      host = "localhost" ,
      port = 6379
    )
  }

  fun getRedisHost(): String = Redis.host
  fun getRedisPort(): Int = Redis.port ?:6379
}
