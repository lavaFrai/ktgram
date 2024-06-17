package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ChatJoinRequestHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val chatJoinRequest = update.chatJoinRequest!!
}