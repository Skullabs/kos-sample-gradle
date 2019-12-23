package kos.sample.simple

import injector.Singleton
import kos.rest.*
import java.util.UUID

@Singleton
@RestApi("/user")
class UserCrudApi(val repository: InMemoryUserRepository) {

    @GET(":id")
    fun retrieveById(
        @Param id: UUID
    ) = repository.retrieveById(id)

    @GET fun retrieveAll() = repository.retrieveAll()

    @POST
    fun insert(
        @Body user: User
    ) = repository.insert(user)

    @PUT(":id")
    fun update(
        @Param id: UUID,
        @Body user: User
    ) = repository.update(user.copy(id = id))

    @DELETE(":id")
    fun delete(
        @Param id: UUID
    ) = repository.delete(id)
}