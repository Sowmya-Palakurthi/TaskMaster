package `in`.porter.taskmaster.client.clients

import `in`.porter.kotlinutils.sqs.coroutines.client.SQSClient
import `in`.porter.taskmaster.api.models.async.AsyncJob
import javax.inject.Inject

class TaskMasterHttpClient
@Inject
constructor(
  private val client: SQSClient
) : TaskMasterClient {

  override suspend fun publishJob(job: AsyncJob) {
    TODO()
  }

}
