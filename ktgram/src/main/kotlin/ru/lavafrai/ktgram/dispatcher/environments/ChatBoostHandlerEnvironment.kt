package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ChatBoostHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val chatBoost = update.chatBoost!!
}