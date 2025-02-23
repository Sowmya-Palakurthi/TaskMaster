package `in`.porter.taskmaster.client.di

import `in`.porter.taskmaster.client.clients.TaskMasterHttpClient
import `in`.porter.taskmaster.client.clients.TaskMasterClient
import dagger.Binds
import dagger.Module

@Module
internal abstract class ClientModule {

  @Binds
  abstract fun provideAsyncJobsClient(client: TaskMasterHttpClient): TaskMasterClient
}
