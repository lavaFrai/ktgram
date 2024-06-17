package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class MyChatMemberHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val myChatMember = update.myChatMember!!
}