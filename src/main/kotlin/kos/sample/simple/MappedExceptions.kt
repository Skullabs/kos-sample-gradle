package kos.sample.simple

import injector.Exposed
import io.vertx.core.json.DecodeException
import kos.api.MutableKosContext
import kos.api.Plugin
import kos.api.Response
import kos.core.exception.PredicateExceptionHandler

@Exposed
class MappedExceptions: Plugin {

    private val exceptionHandler = PredicateExceptionHandler().apply {
        add({ it is NotFound }) { _, _ -> Response.NOT_FOUND }
        add({ it is DecodeException }) { _, cause -> Response.of(cause.message).statusCode(400) }
    }

    override fun configure(kosContext: MutableKosContext) {
        kosContext.exceptionHandler = exceptionHandler
    }
}

class NotFound: RuntimeException()