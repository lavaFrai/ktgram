package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.environments.CallbackQueryHandlerEnvironment
import ru.lavafrai.ktgram.types.Update

open class CallbackQueryHandler(
    vararg filters: Filter,
    val handler: suspend CallbackQueryHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        if (update.callbackQuery != null) return filters.all { it(update) }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = CallbackQueryHandlerEnvironment(update, update.callbackQuery!!, update.callbackQuery.data!!)
        handler(environment)
    }
}