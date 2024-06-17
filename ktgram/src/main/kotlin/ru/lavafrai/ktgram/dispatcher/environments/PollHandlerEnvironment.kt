package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class PollHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val poll = update.poll!!
}