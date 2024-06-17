package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class RemovedChatBoostHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val removedChatBoost = update.removedChatBoost!!
}