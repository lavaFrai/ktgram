package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class MessageHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val message = update.message!!
}