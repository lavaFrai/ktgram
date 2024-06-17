package ru.lavafrau.ktgram.examples.full.handlers

import ru.lavafrai.ktgram.dispatcher.Router
import ru.lavafrai.ktgram.dispatcher.callbackQuery
import ru.lavafrai.ktgram.dispatcher.handle
import ru.lavafrai.ktgram.dispatcher.inlineQuery
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard
import ru.lavafrai.ktgram.types.replymarkup.removeKeyboard
import ru.lavafrai.ktgram.types.replymarkup.replyKeyboard.replyKeyboard
import ru.lavafrau.ktgram.examples.full.keyboards.keyboardTypeSelectKeyboard
import ru.lavafrau.ktgram.examples.full.keyboards.startMenuKeyboard

fun Router<*>.keyboardHandler() {
    callbackQuery("demo.keyboard") {
        handle {
            callbackQuery.message!!.answer("Choose keyboard type which you would like to see.", replyMarkup = keyboardTypeSelectKeyboard)
            callbackQuery.message!!.delete()
        }
    }

    callbackQuery("demo.keyboard.inline") {
        handle {
            callbackQuery.message!!.answer("Inline keyboard", replyMarkup = inlineKeyboard {
                button("Data button", "demo.keyboard.inline.1")
                urlButton("URL button", "https://google.com")
                row {
                    button("Answer button", "demo.keyboard.inline.2")
                    button("Reply button", "demo.keyboard.inline.3")
                }
            })

            callbackQuery.message!!.answer("Choose keyboard type which you would like to see.", replyMarkup = keyboardTypeSelectKeyboard)
            callbackQuery.message!!.delete()
        }
    }

    callbackQuery(startsWith = "demo.keyboard.inline.") {
        handle {
            when (callbackQuery.data) {
                "demo.keyboard.inline.1" -> callbackQuery.answer()
                "demo.keyboard.inline.2" -> callbackQuery.answer("Answer button pressed")
                "demo.keyboard.inline.3" -> callbackQuery.reply("Reply button pressed")
            }
        }
    }

    callbackQuery("demo.keyboard.reply") {
        handle {
            callbackQuery.message!!.answer("Reply keyboard", replyMarkup = replyKeyboard {
                row {
                    button("Reply button")
                    requestUsers("Users request", 0)
                }
                row {
                    requestChat("Chat request", 1, true)
                    requestContact("Contact request")
                }
                row {
                    requestLocation("Location request")
                    requestPoll("Poll request", PollType.REGULAR)
                }
            })

            callbackQuery.message!!.answer("Choose keyboard type which you would like to see.", replyMarkup = keyboardTypeSelectKeyboard)
            callbackQuery.message!!.delete()
        }
    }

    callbackQuery("demo.keyboard.clear") {
        handle {
            callbackQuery.message!!.answer("Keyboard cleared", replyMarkup = removeKeyboard())
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("Choose keyboard type which you would like to see.", replyMarkup = keyboardTypeSelectKeyboard)
        }
    }
}