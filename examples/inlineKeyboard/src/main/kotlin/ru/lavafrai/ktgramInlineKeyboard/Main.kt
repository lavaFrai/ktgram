package ru.lavafrai.ktgramInlinKeyboard

import LoginUrl
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.callback
import ru.lavafrai.ktgram.dispatcher.text
import ru.lavafrai.ktgram.types.replymarkup.ReplyKeyboardRemove
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard

fun Dispatcher.addHandlers() {
    var a = 1

    val exampleKeyboard = inlineKeyboard {
        row {
            button("Button 1", "data1")
            urlButton("Button 2", "https://google.com")
        }
        button("Button 3", "data3")
    }

    text {
        message.reply("Do it:", replyMarkup = exampleKeyboard)
    }

    callback {
        callbackQuery.reply("You've clicked: '${callbackQuery.data}'")
        callbackQuery.message?.editReplyMarkup(ReplyKeyboardRemove())
        callbackQuery.message?.editText("That's all")
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
