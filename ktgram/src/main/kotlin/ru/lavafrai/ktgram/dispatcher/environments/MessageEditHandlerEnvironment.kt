package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class MessageEditHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val message = update.editedMessage!!
}