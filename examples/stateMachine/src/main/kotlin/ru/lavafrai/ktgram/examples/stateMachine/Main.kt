package ru.lavafrai.ktgram.examples.stateMachine

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.stateMachine.setState
import ru.lavafrai.ktgram.stateMachine.state
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.payments.simplePrice

fun Router<*>.addHandlers() {
    command("start") {
        state("known") {
            handle {
                message.answer("Hi there again! I've remembered you!")
            }
        }

        stateLess {
            handle {
                message.answer("Hello, new user!")
                setState("known")
            }
        }
    }
}


fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        addHandlers()
    }

    bot.runPolling()
}