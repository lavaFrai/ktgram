package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class InlineQueryHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val inlineQuery = update.inlineQuery!!
}