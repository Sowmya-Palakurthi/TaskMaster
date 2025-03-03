package `in`.porter.taskmaster.api.service.usecases

import `in`.porter.taskmaster.domain.Entities.UserEntities.UserResponse
import `in`.porter.taskmaster.domain.usecases.GetUserService
import javax.inject.Inject

class GetUser @Inject constructor(
    private val getUserService: GetUserService
) {
    suspend fun invoke (userId:Int): UserResponse? {
         return getUserService.invoke(userId)

        }
    }
