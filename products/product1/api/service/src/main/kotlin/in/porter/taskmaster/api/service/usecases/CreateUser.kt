package `in`.porter.taskmaster.api.service.usecases



import `in`.porter.taskmaster.api.models.Entities.ApiUserEntities.UserApiRequest
import `in`.porter.taskmaster.api.service.RequestMapper
import `in`.porter.taskmaster.domain.usecases.CreateUserService
import javax.inject.Inject

class CreateUser @Inject constructor(

    private val createUserService: CreateUserService,
    private val userMapper: RequestMapper
) {
    suspend fun invoke(apiModel: UserApiRequest):String{
        val domainTask = userMapper.toDomain(apiModel)
        return createUserService.invoke(domainTask)
    }
}