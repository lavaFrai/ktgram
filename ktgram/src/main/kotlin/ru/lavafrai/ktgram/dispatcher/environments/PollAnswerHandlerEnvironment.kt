package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class PollAnswerHandlerEnvironment(update: Update): HandlerEnvironment(update) {
    val pollAnswer = update.pollAnswer!!
}