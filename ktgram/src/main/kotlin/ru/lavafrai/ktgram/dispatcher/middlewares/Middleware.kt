package ru.lavafrai.ktgram.dispatcher.middlewares

import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.Handler
import ru.lavafrai.ktgram.types.Update


/**
 * This class represents a middleware in the dispatching process.
 * A middleware can perform actions before and after the execution of a handler.
 * Also, outer middlewares can be used to cancel the execution of a handler.
 *
 * @property dispatcher The dispatcher that this middleware is associated with.
 */
open class Middleware(val dispatcher: Dispatcher) {
    /**
     * This function is called before the execution of a handler.
     * It can be overridden to perform actions before the handler is executed.
     *
     * @param update The update that the handler will process.
     */
    open suspend fun beforeAction(update: Update): Update { return update }

    /**
     * This function is called after the execution of a handler.
     * It can be overridden to perform actions after the handler has finished executing.
     *
     * @param update The update that the handler has processed.
     */
    open suspend fun afterAction(update: Update, handlerExecutionResult: UpdateResult) {}
}