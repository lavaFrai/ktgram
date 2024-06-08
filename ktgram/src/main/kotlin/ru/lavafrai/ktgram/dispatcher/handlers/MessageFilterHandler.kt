package ru.lavafrai.ktgram.dispatcher.handlers

import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.types.Update

class MessageFilterHandler(vararg filters: Filter, val handler: suspend MessageHandlerEnvironment.() -> Unit): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        return filters.all { it(update) }
    }

    override suspend fun handle(update: Update) {
        val environment = MessageHandlerEnvironment(update, update.message!!)
        handler(environment)
    }
}