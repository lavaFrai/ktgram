package ru.lavafrai.ktgramfilters

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.handlers.MessageFilter
import ru.lavafrai.ktgram.dispatcher.message
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType

fun Dispatcher.addHandlers() {
    val f: MessageFilter = {
        update.message?.text?.startsWith("/") ?: false
    }

    messageFilter(f) {
        message.reply("Command: ${update.message!!.text!!.removePrefix("/")}")
    }

    message {
        bot.sendMessage(update.message!!.chat.id, "Echo: ${update.message!!.text}", protectContent = true)
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