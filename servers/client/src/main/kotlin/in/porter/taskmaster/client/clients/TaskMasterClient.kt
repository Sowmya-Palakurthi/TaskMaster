package `in`.porter.taskmaster.client.clients

import `in`.porter.taskmaster.api.models.async.AsyncJob


interface TaskMasterClient {
  suspend fun publishJob(job: AsyncJob)
}
