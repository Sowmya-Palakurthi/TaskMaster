package `in`.porter.taskmaster.servers.commons.di.factories


import `in`.porter.taskmaster.data.di.DaggerPsqlDataComponent
import `in`.porter.taskmaster.servers.commons.di.components.RootComponent
import `in`.porter.taskmaster.data.di.PsqlDataComponent
object PsqlDataComponentFactory {
  fun build(rootComponent: RootComponent): PsqlDataComponent =
    DaggerPsqlDataComponent.builder()
      .database(rootComponent.database)
      .meterRegistry(rootComponent.meterRegistry)
      .jedis(rootComponent.jedis)
      .build()
}
