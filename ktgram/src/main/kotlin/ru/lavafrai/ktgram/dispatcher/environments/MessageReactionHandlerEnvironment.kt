package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class MessageReactionHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val messageReaction = update.messageReaction!!
}