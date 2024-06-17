package ru.lavafrai.ktgram.examples.echo

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*

fun Router<*>.addHandlers() {
    text {
        handle {
            message.reply("You said: ${update.message!!.text}")
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