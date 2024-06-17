package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class EditedBusinessMessageHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val editedBusinessMessage = update.editedBusinessMessage!!
}