package ru.lavafrai.ktgram.dispatcher

import org.slf4j.Logger
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.environments.ErrorEnvironment
import ru.lavafrai.ktgram.dispatcher.middlewares.CancelUpdateException
import ru.lavafrai.ktgram.dispatcher.middlewares.Middleware
import ru.lavafrai.ktgram.dispatcher.middlewares.UpdateCancellationCause
import ru.lavafrai.ktgram.dispatcher.middlewares.UpdateResult
import ru.lavafrai.ktgram.types.Update

class Dispatcher(
    val bot: Bot,
    val strategy: DispatcherStrategy = DispatcherStrategy.HANDLE_ONE,
    val logger: Logger = bot.logger,
) {
    enum class DispatcherStrategy {
        HANDLE_ONE,
        HANDLE_ALL,
    }

    private val routers = mutableListOf<Router<*>>()
    private val middlewares = mutableListOf<Middleware>()
    private val outerMiddlewares = mutableListOf<Middleware>()
    private val errorHandlers = mutableListOf<Handler<ErrorEnvironment>>()

    suspend fun handleUpdate(update: Update) {
        val result = handleUpdateToResult(update)

        if (!result.hasCanceled) runInnerMiddlewaresAfter(update, result)
        runOuterMiddlewaresAfter(update, result)

        if (result.hasException) {
            val errorEnvironment = ErrorEnvironment(update, result.exception!!)
            for (handler in errorHandlers) {
                try {
                    handler(errorEnvironment)
                } catch (e: Exception) {
                    logger.error("Error while running error handler with update=${update.updateId} type=${update.type}", e)
                }
            }
        }
    }

    private suspend fun handleUpdateToResult(update: Update): UpdateResult {
        var exception: Exception? = null
        var canceled: Boolean = false
        var processedUpdate = update

        try {
            processedUpdate = runOuterMiddlewaresBefore(processedUpdate)
        } catch (e: CancelUpdateException) {
            val result = UpdateResult(hasException = false, workTime = 0, hasCanceled = true, cancellationCause = UpdateCancellationCause.CANCELED_BY_MIDDLEWARE)
        }

        var handlers = routers.filter { it.canHandle(update) }
        if (handlers.isEmpty()) {
            return UpdateResult(hasException = false, workTime = 0, hasCanceled = true, cancellationCause = UpdateCancellationCause.NO_HANDLER_FOUND)
        }
        if (strategy == DispatcherStrategy.HANDLE_ONE) {
            handlers = listOf(handlers.first())
        }

        try {
            processedUpdate = runInnerMiddlewaresBefore(processedUpdate)
        } catch (e: CancelUpdateException) {
            return UpdateResult(hasException = false, workTime = 0, hasCanceled = true, cancellationCause = UpdateCancellationCause.CANCELED_BY_MIDDLEWARE)
        }

        var workTime = 0L
        for (handler in handlers) {
            try {
                val start = System.currentTimeMillis()
                handler.handleUpdate(processedUpdate)
                workTime += System.currentTimeMillis() - start
            } catch (e: Exception) {
                return UpdateResult(hasException = true, exception = e, workTime = 0, hasCanceled = false)
            }
        }

        return UpdateResult(hasException = false, workTime = workTime, hasCanceled = false)
    }

    private suspend fun runMiddlewaresBefore(update: Update, middlewares: List<Middleware>): Update {
        var processedUpdate = update
        for (middleware in middlewares) {
            try {
                processedUpdate = middleware.beforeAction(processedUpdate)
            } catch (e: CancelUpdateException) {
                throw e
            } catch (e: Exception) {
                logger.error("Error while running middleware before with update=${update.updateId} type=${update.type}", e)
                continue
            }
        }
        return processedUpdate
    }

    private suspend fun runOuterMiddlewaresBefore(update: Update): Update {
        return runMiddlewaresBefore(update, outerMiddlewares)
    }

    private suspend fun runInnerMiddlewaresBefore(update: Update): Update {
        return runMiddlewaresBefore(update, middlewares)
    }

    private suspend fun runMiddlewaresAfter(update: Update, middlewares: List<Middleware>, result: UpdateResult) {
        for (middleware in middlewares) {
            try {
                middleware.afterAction(update, result)
            } catch (e: Exception) {
                logger.error("Error while running middleware after with update=${update.updateId} type=${update.type}", e)
                continue
            }
        }
    }

    private suspend fun runOuterMiddlewaresAfter(update: Update, result: UpdateResult) {
        runMiddlewaresAfter(update, outerMiddlewares, result)
    }

    private suspend fun runInnerMiddlewaresAfter(update: Update, result: UpdateResult) {
        runMiddlewaresAfter(update, middlewares, result)
    }

    fun addRouter(router: Router<*>) {
        routers.add(router)
    }

    fun addMiddleware(middleware: Middleware) {
        middlewares.add(middleware)
    }

    fun addOuterMiddleware(middleware: Middleware) {
        outerMiddlewares.add(middleware)
    }

    fun addErrorHandler(handler: Handler<ErrorEnvironment>) {
        errorHandlers.add(handler)
    }
}