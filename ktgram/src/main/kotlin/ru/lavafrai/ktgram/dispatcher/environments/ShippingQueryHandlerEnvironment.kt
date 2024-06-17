package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ShippingQueryHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val shippingQuery = update.shippingQuery!!
}
