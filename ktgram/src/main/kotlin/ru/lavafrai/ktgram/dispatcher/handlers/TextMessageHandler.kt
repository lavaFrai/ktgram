package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.types.Update

open class TextMessageHandler(
    val handler: suspend MessageHandlerEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    override suspend fun predict(update: Update): Boolean {
        return update.message?.text != null
    }

    override suspend fun handle(update: Update) {
        val environment = MessageHandlerEnvironment(update, update.message!!)
        handler(environment)
    }
}