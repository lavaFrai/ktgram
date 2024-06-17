package ru.lavafrau.ktgram.examples.full.handlers

import ru.lavafrai.ktgram.dispatcher.Router
import ru.lavafrai.ktgram.dispatcher.callbackQuery
import ru.lavafrai.ktgram.dispatcher.command
import ru.lavafrai.ktgram.dispatcher.handle
import ru.lavafrau.ktgram.examples.full.keyboards.startMenuKeyboard

fun Router<*>.startHandler() {
    command("start") {
        handle {
            update.message!!.answer("Hi! Please choose which category you would like to see.", replyMarkup = startMenuKeyboard)
            update.message!!.delete()
        }
    }

    callbackQuery("demo") {
        handle {
            callbackQuery.message!!.answer("Choose which category you would like to see.", replyMarkup = startMenuKeyboard)
            callbackQuery.message!!.delete()
        }
    }
}
