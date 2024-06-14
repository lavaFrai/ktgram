package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.types.Update
import ru.lavafrai.ktgram.types.UpdateType
import ru.lavafrai.ktgram.types.payments.PreCheckoutQuery


class PreCheckoutQueryFilterEnvironment(update: Update, val query: PreCheckoutQuery, val payload: String): FilterEnvironment(update)
typealias PreCheckoutQueryFilter = suspend PreCheckoutQueryFilterEnvironment.() -> Boolean

open class PreCheckoutQueryHandler(
    vararg filters: PreCheckoutQueryFilter,
    val handler: suspend PreCheckoutQueryFilterEnvironment.() -> Unit,
    dispatcher: Dispatcher,
    bot: Bot = dispatcher.bot
): Handler {
    private val filters = filters.toList()

    override suspend fun predict(update: Update): Boolean {

        if (update.type == UpdateType.PreCheckoutQuery) {
            val env = PreCheckoutQueryFilterEnvironment(update, update.preCheckoutQuery!!, update.preCheckoutQuery.invoicePayload)
            return filters.all { it(env) }
        }
        return false
    }

    override suspend fun handle(update: Update) {
        val environment = PreCheckoutQueryFilterEnvironment(update, update.preCheckoutQuery!!, update.preCheckoutQuery.invoicePayload)
        handler(environment)
    }
}