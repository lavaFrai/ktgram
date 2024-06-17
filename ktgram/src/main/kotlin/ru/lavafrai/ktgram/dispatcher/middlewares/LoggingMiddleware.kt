package ru.lavafrai.ktgram.dispatcher.middlewares

import org.slf4j.Logger
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.types.Update

class LoggingMiddleware(dispatcher: Dispatcher, val logger: Logger = dispatcher.logger): Middleware(dispatcher) {
    override suspend fun afterAction(update: Update, handlerExecutionResult: UpdateResult) {
        if (handlerExecutionResult.hasCanceled) {
            logger.info("Update id=${update.updateId} type=${update.type} is not handled by bot id=${dispatcher.bot.id} cause=${handlerExecutionResult.cancellationCause}")
            return
        }

        if (handlerExecutionResult.hasException) {
            logger.error("Update id=${update.updateId} type=${update.type} is not handled by bot id=${dispatcher.bot.id} cause=${handlerExecutionResult.exception}", handlerExecutionResult.exception)
            return
        }

        logger.info("Update id=${update.updateId} type=${update.type} is handled by bot id=${dispatcher.bot.id} in ${handlerExecutionResult.workTime}ms")
    }
}