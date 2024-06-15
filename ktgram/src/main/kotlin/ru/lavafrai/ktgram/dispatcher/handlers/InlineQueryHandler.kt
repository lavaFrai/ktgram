package ru.lavafrai.ktgram.dispatcher.handlers

import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.environments.InlineQueryHandlerEnvironment
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.Update
import ru.lavafrai.ktgram.types.inline.InlineQuery


class InlineQueryFilterEnvironment(update: Update, val query: InlineQuery): FilterEnvironment(update)

typealias InlineQueryFilter = suspend InlineQueryFilterEnvironment.() -> Boolean

class InlineQueryHandler(
    vararg filters: InlineQueryFilter,
    val handler: suspend InlineQueryHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        if (update.inlineQuery != null) {
            val env = InlineQueryFilterEnvironment(update, update.inlineQuery)
            return filters.all { env.it() }
        }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = InlineQueryHandlerEnvironment(update, update.inlineQuery!!)
        handler(environment)
    }
}