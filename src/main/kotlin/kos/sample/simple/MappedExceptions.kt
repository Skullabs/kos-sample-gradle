package kos.sample.simple

import injector.ExposedAs
import io.vertx.core.json.DecodeException
import io.vertx.core.logging.Logger
import io.vertx.ext.web.RoutingContext
import kos.core.ExceptionHandler
import kos.core.Response

@ExposedAs(ExceptionHandler::class)
class MappedExceptions(val logger: Logger): ExceptionHandler {

    override fun handle(context: RoutingContext, cause: Throwable): Response {
        return when (cause) {
            is DecodeException -> Response.of(cause.message).statusCode(400)
            is NotFound -> Response.NOT_FOUND
            else -> internalServerError(cause)
        }
    }

    private fun internalServerError(cause: Throwable): Response {
        logger.error("Something terrible just happened", cause)
        return Response.empty(500)
    }
}

class NotFound: RuntimeException()