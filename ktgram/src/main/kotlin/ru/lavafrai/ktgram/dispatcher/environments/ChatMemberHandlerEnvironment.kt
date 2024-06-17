package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ChatMemberHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val chatMember = update.chatMember!!
}