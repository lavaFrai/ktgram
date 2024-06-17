package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ChannelPostHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val message = update.channelPost!!
}