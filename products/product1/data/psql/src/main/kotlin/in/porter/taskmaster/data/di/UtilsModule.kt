package `in`.porter.taskmaster.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import `in`.porter.taskmaster.data.UserRepositoryImplementation
import `in`.porter.taskmaster.data.mapperclasses.TaskMapperActions
import `in`.porter.taskmaster.domain.UserRepositoryActions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
internal object UtilsModule {

  @Provides
  @JvmStatic
  fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO



}
