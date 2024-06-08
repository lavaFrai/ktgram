package ru.lavafrai.ktgramecho

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.text

fun Dispatcher.addHandlers() {
    text {
        println(update.message!!.reply("You said: ${update.message!!.text}"))
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