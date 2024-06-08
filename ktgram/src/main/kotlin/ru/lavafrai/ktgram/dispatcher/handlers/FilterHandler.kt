package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.Update


typealias Filter = suspend (update: Update) -> Boolean

class FilterHandler(vararg filters: Filter, val handler: suspend UpdateHandlerEnvironment.() -> Unit): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        return filters.all { it(update) }
    }

    override suspend fun handle(update: Update) {
        val environment = UpdateHandlerEnvironment(update)
        handler(environment)
    }
}