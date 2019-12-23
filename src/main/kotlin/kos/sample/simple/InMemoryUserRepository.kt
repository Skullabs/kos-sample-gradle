package kos.sample.simple

import injector.Singleton
import java.util.UUID

@Singleton
class InMemoryUserRepository {

    val data = mutableMapOf<UUID, User>()

    fun update(user: User) {
        retrieveById(user.id)
        save(user)
    }

    fun insert(user: User) = save(user)

    fun save(user: User) {
        data[user.id] = user
    }

    fun delete(id: UUID) {
        data.remove(id) ?: throw NotFound()
    }

    fun retrieveById(id: UUID) = data[id] ?: throw NotFound()

    fun retrieveAll() = data.values
}

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String
)