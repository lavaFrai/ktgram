package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class DeletedBusinessMessagesHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val deletedBusinessMessages = update.deletedBusinessMessages!!
}