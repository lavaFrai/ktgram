package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ChosenInlineResultHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val chosenInlineResult = update.chosenInlineResult!!
}