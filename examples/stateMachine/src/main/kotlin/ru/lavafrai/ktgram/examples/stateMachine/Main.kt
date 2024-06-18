package ru.lavafrai.ktgram.examples.stateMachine

import org.intellij.lang.annotations.Language
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.environments.UpdateEnvironment
import ru.lavafrai.ktgram.stateMachine.*
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard

fun Router<*>.addHandlers() {
    command("start") {
        handle {
            data.clear()
            message.answer("Hello! What is your name?")
            setState(StartStates.WaitingName) // Transition to the next state
        }
    }

    state(StartStates.WaitingName) {
        text {
            val likeBotsKeyboard = inlineKeyboard {
                row {
                    button("Yes!", "yes")
                    button("No ", "no")
                }
            }

            handle {
                val name = message.text!!
                data.set("name", name)
                setState(StartStates.WaitingLikeBots)

                message.answer("Nice to meet you, $name!\nDo you like to write bots?", replyMarkup = likeBotsKeyboard)
            }
        }
    }

    state(StartStates.WaitingLikeBots) {
        callbackQuery("yes") {
            handle {
                query.message!!.answer("Awesome! I like to develop bots to!\nSo, what's your favorite programming language for that?")
                setState(StartStates.WaitingLanguage)

                query.answer()
            }
        }

        callbackQuery("no") {
            handle {
                query.message!!.answer("Not bad not terrible.\nSee you soon.")
                clearState()

                sendSummary(
                    update.chat!!,
                    data.get("name")!!,
                    false,
                    null,
                )

                query.answer()
            }
        }
    }

    state(StartStates.WaitingLanguage) {
        text {
            handle {
                val language = message.text!!.lowercase()
                clearState()

                if (language == "kotlin") message.answer("Kotlin, you say? That's great choice! Good luck with that!")
                if (language == "python") message.answer("Oh. That's good choice but did you try Kotlin?")

                sendSummary(
                    message.chat,
                    data.get("name")!!,
                    true,
                    language
                )

                data.clear()
            }
        }
    }
}

suspend fun sendSummary(chat: Chat, name: String, likeBots: Boolean, language: String?) {
    val summary = buildString {
        append("Summary:\n")
        append("Name: $name\n")
        append("Like bots: ${if (likeBots) "Yes" else "No"}\n")
        append("Favourite language: ${language ?: "Not specified"}")
    }

    chat.sendText(summary)
}

sealed class StartStates {
    object WaitingName: State()
    object WaitingLikeBots: State()
    object WaitingLanguage: State()
}

fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        addHandlers()
    }

    bot.runPolling()
}