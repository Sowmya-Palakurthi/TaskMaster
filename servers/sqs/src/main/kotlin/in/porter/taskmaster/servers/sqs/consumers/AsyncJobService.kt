package `in`.porter.taskmaster.servers.sqs.consumers

import `in`.porter.taskmaster.api.models.async.AsyncJob
import javax.inject.Inject

class AsyncJobService
@Inject
constructor(
) {

  suspend fun invoke(job: AsyncJob) {
     TODO()
  }
}
