package usecases



import Mappers.RequestMapper
import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiRequest
import `in`.porter.taskmaster.domain.usecases.CreateUserService
import javax.inject.Inject

class CreateUser @Inject constructor(

    private val createUserService: CreateUserService,
    private val userMapper: RequestMapper
) {
    suspend fun invoke(apiModel: UserApiRequest) {
        val domainTask = userMapper.toDomain(apiModel)
        createUserService.invoke(domainTask)
    }
}