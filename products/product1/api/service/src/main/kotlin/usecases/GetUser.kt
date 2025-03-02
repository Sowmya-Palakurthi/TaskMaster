package usecases

import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiRequest
import `in`.porter.taskmaster.domain.RepositoryActions.UserRepositoryActions
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: UserRepositoryActions,
    private val userMapper: RequestMapper
) {
    suspend fun invoke (userApiRequest: UserApiRequest) {
        val domainUser = userMapper.toDomain(userApiRequest)
        repository.getUser(domainUser.uid)
    }
}