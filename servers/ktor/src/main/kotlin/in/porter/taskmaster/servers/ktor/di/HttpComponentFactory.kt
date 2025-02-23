package `in`.porter.taskmaster.servers.ktor.di

import `in`.porter.taskmaster.servers.commons.di.factories.ComponentsFactory

object HttpComponentFactory {

  fun build(): HttpComponent =
    DaggerHttpComponent.builder()
      .rootComponent(ComponentsFactory.rootComponent)
      .psqlDataComponent(ComponentsFactory.psqlDataComponent)
      .build()

}
