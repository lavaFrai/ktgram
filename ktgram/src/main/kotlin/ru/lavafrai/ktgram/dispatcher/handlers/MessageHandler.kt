package ru.lavafrai.ktgram.dispatcher.handlers

import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.Update


class MessageFilterEnvironment(update: Update, val message: Message): FilterEnvironment(update)

typealias MessageFilter = suspend MessageFilterEnvironment.() -> Boolean

class MessageHandler(
    vararg filters: MessageFilter,
    val handler: suspend MessageHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        if (update.message != null) {
            val env = MessageFilterEnvironment(update, update.message)
            return filters.all { env.it() }
        }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = MessageHandlerEnvironment(update, update.message!!)
        handler(environment)
    }
}