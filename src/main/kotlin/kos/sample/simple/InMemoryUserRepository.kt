package kos.sample.simple

import injector.Singleton
import io.vertx.core.Future
import java.util.UUID

@Singleton
class InMemoryUserRepository {

    val data = mutableMapOf<UUID, User>()

    fun update(user: User) {
        retrieveById(user.id)
        save(user)
    }

    fun insert(user: User) = save(user)

    fun save(user: User): UUID {
        data[user.id] = user
        return user.id
    }

    fun delete(id: UUID): Future<Void> = when (data.remove(id)) {
        null -> Future.failedFuture(NotFound())
        else -> Future.succeededFuture()
    }

    fun retrieveById(id: UUID) = data[id] ?: throw NotFound()

    fun retrieveAll() = data.values
}

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String
)