package kos.sample.simple

import io.vertx.core.Future
import kos.events.Publisher
import java.util.*

interface UserEventPublisher {

    @Publisher("mem::user::deleted")
    fun trigger(event: UserHasBeenDeleted): Future<Void>
}

data class UserHasBeenDeleted(val userId: UUID)