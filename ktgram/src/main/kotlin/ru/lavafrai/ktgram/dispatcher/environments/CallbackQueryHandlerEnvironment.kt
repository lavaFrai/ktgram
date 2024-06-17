package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class CallbackQueryHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val callbackQuery = update.callbackQuery!!
}