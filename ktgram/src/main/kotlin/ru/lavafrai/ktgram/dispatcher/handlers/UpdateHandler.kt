package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.Update

open class UpdateHandler(
    val handler: suspend UpdateHandlerEnvironment.() -> Unit,
    val dispatcher: Dispatcher,
    val bot: Bot = dispatcher.bot,
): Handler {
    override suspend fun predict(update: Update): Boolean {
        return true
    }

    override suspend fun handle(update: Update) {
        val environment = UpdateHandlerEnvironment(update)
        handler(environment)
    }
}
