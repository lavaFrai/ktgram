package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update
import ru.lavafrai.ktgram.types.inline.InlineQuery

class InlineQueryHandlerEnvironment(update: Update, val query: InlineQuery): UpdateHandlerEnvironment(update) {
}