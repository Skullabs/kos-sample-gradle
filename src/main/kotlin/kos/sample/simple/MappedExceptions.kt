package kos.sample.simple

import injector.Exposed
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.json.DecodeException
import kos.api.ExceptionHandler
import kos.api.Response
import org.slf4j.Logger

@Exposed
class MappedExceptions(private val logger: Logger): ExceptionHandler {

    override fun handle(request: HttpServerRequest, cause: Throwable): Response {
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