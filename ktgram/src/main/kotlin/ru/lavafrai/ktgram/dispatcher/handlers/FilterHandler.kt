package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.dispatcher.environments.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.Update


open class FilterEnvironment(val update: Update)

typealias Filter = suspend FilterEnvironment.() -> Boolean

class FilterHandler(vararg filters: Filter, val handler: suspend UpdateHandlerEnvironment.() -> Unit): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {
        val env = FilterEnvironment(update)
        return filters.all { it(env) }
    }

    override suspend fun handle(update: Update) {
        val environment = UpdateHandlerEnvironment(update)
        handler(environment)
    }
}