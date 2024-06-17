package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class BusinessConnectionHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val businessConnection = update.businessConnection!!
}