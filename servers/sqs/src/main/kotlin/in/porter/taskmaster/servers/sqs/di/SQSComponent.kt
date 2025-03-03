package `in`.porter.taskmaster.servers.sqs.di

import `in`.porter.kotlinutils.sqs.coroutines.drain.stream.StreamQueueDrainer
import `in`.porter.taskmaster.api.models.async.AsyncJob
import `in`.porter.taskmaster.data.di.PsqlDataComponent
import `in`.porter.taskmaster.servers.commons.di.components.RootComponent
import `in`.porter.taskmaster.servers.commons.usecases.external.Run
import dagger.Component

@SQSScope
@Component(
  dependencies = [
    RootComponent::class,
    PsqlDataComponent::class
  ],
  modules = [
    SQSModule::class,
  ]
)
interface SQSComponent {
  val run: Run
  val drainer: StreamQueueDrainer<AsyncJob>
}
