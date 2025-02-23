package `in`.porter.taskmaster.servers.ktor.di

import `in`.porter.taskmaster.servers.commons.di.components.RootComponent
import `in`.porter.taskmaster.servers.commons.usecases.external.Run
import `in`.porter.taskmaster.data.di.PsqlDataComponent

import dagger.Component

@HttpScope
@Component(
  dependencies = [
    RootComponent::class,
    PsqlDataComponent::class
  ]
)
interface HttpComponent {
  val run: Run
}
