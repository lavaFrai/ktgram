package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class BusinessMessageHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val businessMessage = update.businessMessage!!
}