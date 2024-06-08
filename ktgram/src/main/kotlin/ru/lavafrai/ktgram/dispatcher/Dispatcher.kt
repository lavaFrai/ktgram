package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.handlers.Handler
import ru.lavafrai.ktgram.dispatcher.handlers.UpdateHandler
import ru.lavafrai.ktgram.types.Update

class Dispatcher(
    val bot: Bot,
) {
    private val updateHandlers = mutableListOf<Handler>()

    fun handling(configure: Dispatcher.() -> Unit) {
        configure()
    }

    suspend fun handleUpdate(update: Update): Boolean {
        updateHandlers.forEach { handler ->
            if (handler.predict(update)) {
                handler.handle(update)
                return true
            }
        }

        return false
    }

    fun addHandler(handler: Handler) {
        updateHandlers.add(handler)
    }
}