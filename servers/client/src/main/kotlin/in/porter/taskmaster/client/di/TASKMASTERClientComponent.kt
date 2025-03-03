package `in`.porter.taskmaster.client.di

import `in`.porter.taskmaster.client.clients.TaskMasterClient
import `in`.porter.taskmaster.client.config.ClientConfig
import dagger.BindsInstance
import dagger.Component

@ClientScope
@Component(
  modules = [
    ClientModule::class,
    UtilsModule::class
  ]
)
interface TASKMASTERClientComponent {
  val taskmasterClient: TaskMasterClient

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun config(config: ClientConfig): Builder

    fun build(): TASKMASTERClientComponent
  }
}
