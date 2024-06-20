package ru.lavafrai.ktgram.examples.echo

import kotlinx.coroutines.delay
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.types.ChatAction
import ru.lavafrai.ktgram.utils.using

fun Router<*>.addHandlers() {
    text {
        handle {
            message.reply("You said: ${message.text}")
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