package ru.lavafrai.ktgramInlineKeyboard

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.types.replymarkup.forceReply
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard


val exampleKeyboard = inlineKeyboard {
    row {
        button("Button 1", "data1")
        urlButton("Button 2", "https://google.com")
    }
    button("Force Reply", "data3")
}

fun Router<*>.addHandlers() {
    text {
        handle {
            message.reply("Do it:", replyMarkup = exampleKeyboard)
        }
    }

    callbackQuery(startsWith = "data3") {
        handle {
            callbackQuery.message?.editReplyMarkup(inlineKeyboard {})
            callbackQuery.message?.reply("You will reply to this", replyMarkup = forceReply("Enter something"))
        }
    }

    callbackQuery(notStartsWith = "data3") {
        handle {
            callbackQuery.reply("You've clicked: '${callbackQuery.data}'")
            callbackQuery.message?.editReplyMarkup(inlineKeyboard {})
            callbackQuery.message?.editText("That's all")
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
