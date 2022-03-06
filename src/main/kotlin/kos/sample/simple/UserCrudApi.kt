package kos.sample.simple

import injector.Singleton
import io.vertx.core.Future
import io.vertx.core.http.HttpHeaders
import kos.api.Response
import kos.rest.*
import java.util.UUID

@Singleton
@RestApi("/user")
class UserCrudApi constructor(
    private val repository: InMemoryUserRepository,
    private val userEventPublisher: UserEventPublisher,
) {

    @GET(":id")
    fun retrieveById(
        @Param id: UUID
    ) = repository.retrieveById(id)

    @GET fun retrieveAll() = repository.retrieveAll()

    @POST
    fun insert(
        @Body user: User
    ): Response {
        val userId = repository.save(user)
        return Response.CREATED.addHeader(HttpHeaders.LOCATION, "/user/$userId")
    }

    @PUT(":id")
    fun update(
        @Param id: UUID,
        @Body user: User
    ) = repository.update(user.copy(id = id))

    @DELETE(":id")
    fun delete(
        @Param id: UUID
    ): Future<Response> =
        repository.delete(id)
            .compose { userEventPublisher.trigger(UserHasBeenDeleted(id)) }
            .map { Response.NO_CONTENT }
}