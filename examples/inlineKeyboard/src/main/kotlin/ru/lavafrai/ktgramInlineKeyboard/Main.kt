package ru.lavafrai.ktgramInlineKeyboard

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.callback
import ru.lavafrai.ktgram.dispatcher.handlers.CallbackQueryFilter
import ru.lavafrai.ktgram.dispatcher.text
import ru.lavafrai.ktgram.types.replymarkup.ReplyKeyboardRemove
import ru.lavafrai.ktgram.types.replymarkup.forceReply
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard
import ru.lavafrai.ktgram.types.replymarkup.removeKeyboard

fun Dispatcher.addHandlers() {
    val exampleKeyboard = inlineKeyboard {
        row {
            button("Button 1", "data1")
            urlButton("Button 2", "https://google.com")
        }
        button("Force Reply", "data3")
    }

    text {
        message.reply("Do it:", replyMarkup = exampleKeyboard)
    }

    val f: CallbackQueryFilter = { data == "data3" }

    callback(f) {
        callbackQuery.message?.editReplyMarkup(removeKeyboard())
        callbackQuery.message?.reply("You will reply to this", replyMarkup = forceReply("Enter something"))
    }

    callback {
        callbackQuery.reply("You've clicked: '${callbackQuery.data}'")
        callbackQuery.message?.editReplyMarkup(removeKeyboard())
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
