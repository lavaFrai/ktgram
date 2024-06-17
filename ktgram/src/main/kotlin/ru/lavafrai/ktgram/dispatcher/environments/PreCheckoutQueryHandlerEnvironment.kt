package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class PreCheckoutQueryHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val preCheckoutQuery = update.preCheckoutQuery!!
}