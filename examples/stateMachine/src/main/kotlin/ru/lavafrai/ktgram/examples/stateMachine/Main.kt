package ru.lavafrai.ktgram.examples.stateMachine

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.stateMachine.setState
import ru.lavafrai.ktgram.stateMachine.state
import ru.lavafrai.ktgram.types.payments.simplePrice

fun Dispatcher.addHandlers() {
    command("start") {
        val currentState = state.getState(update)
        if (currentState == null) {
            message.answer("Hi there, new member!")
            setState("known")
        } else {
            message.answer("Hi again!")
        }
    }
}


fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    val dispatcher = bot.dispatcher
    dispatcher.handling {
        addHandlers()
    }

    bot.runPolling()
}