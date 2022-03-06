package kos.sample.simple

import injector.Singleton
import kos.events.Listener
import org.slf4j.Logger

@Singleton
class UserEventListener constructor(
    private val logger: Logger
) {

    @Listener("mem::user::deleted")
    fun on(event: UserHasBeenDeleted){
        logger.info("User ${event.userId} has been deleted")
    }
}