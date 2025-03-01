package `in`.porter.taskmaster.servers.commons.di.factories

import  `in`.porter.taskmaster.servers.commons.di.components.DaggerRootComponent
import org.jetbrains.exposed.sql.Database

object ComponentsFactory {

  val rootComponent = DaggerRootComponent.create()
  val psqlDataComponent = PsqlDataComponentFactory.build(rootComponent)
}

