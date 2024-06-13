package ru.lavafrai.ktgram.dispatcher.handlers

import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.types.Update

class MessageFilterHandler(
    vararg filters: Filter,
    val handler: suspend MessageHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        if (update.message != null) return filters.all { it(update) }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = MessageHandlerEnvironment(update, update.message!!)
        handler(environment)
    }
}