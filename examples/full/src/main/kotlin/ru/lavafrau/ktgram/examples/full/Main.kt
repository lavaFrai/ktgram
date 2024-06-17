package ru.lavafrau.ktgram.examples.full

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.middlewares.LoggingMiddleware
import ru.lavafrai.ktgram.dispatcher.middlewares.Middleware
import ru.lavafrai.ktgram.types.Update
import ru.lavafrau.ktgram.examples.full.handlers.keyboardHandler
import ru.lavafrau.ktgram.examples.full.handlers.messagesHandler
import ru.lavafrau.ktgram.examples.full.handlers.startHandler

fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        startHandler()
        messagesHandler()
        keyboardHandler()

        command("err") {
            handle {
                throw RuntimeException("This is an error")
            }
        }
        
        errorHandler {
            update.chat?.id?.let { chatId ->
                bot.sendMessage(chatId, "An error occurred: $error")
            }
        }
    }

    bot.runPolling()
}