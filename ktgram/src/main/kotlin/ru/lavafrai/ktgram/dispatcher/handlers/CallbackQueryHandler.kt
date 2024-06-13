package ru.lavafrai.ktgram.dispatcher.handlers

import CallbackQuery
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.environments.CallbackQueryHandlerEnvironment
import ru.lavafrai.ktgram.types.Update


class CallbackQueryFilterEnvironment(update: Update, val callback: CallbackQuery, val data: String): FilterEnvironment(update)
typealias CallbackQueryFilter = suspend CallbackQueryFilterEnvironment.() -> Boolean

open class CallbackQueryHandler(
    vararg filters: CallbackQueryFilter,
    val handler: suspend CallbackQueryHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {

        if (update.callbackQuery != null) {
            val env = CallbackQueryFilterEnvironment(update, update.callbackQuery, update.callbackQuery.data!!)
            return filters.all { it(env) }
        }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = CallbackQueryHandlerEnvironment(update, update.callbackQuery!!, update.callbackQuery.data!!)
        handler(environment)
    }
}