package ru.lavafrai.ktgram.types.replymarkup.replyKeyboard

import KeyboardButtonPollType
import KeyboardButtonRequestUsers
import PollType
import WebAppInfo
import jdk.jshell.PersistentSnippet

class ReplyKeyboardRowEnvironment() {
    private val buttons: MutableList<KeyboardButton> = mutableListOf()

    fun button(text: String) {
        buttons.add(KeyboardButton(text))
    }

    fun requestContact(text: String) {
        buttons.add(KeyboardButton(text, requestContact = true))
    }

    fun requestLocation(text: String) {
        buttons.add(KeyboardButton(text, requestLocation = true))
    }

    fun requestPoll(text: String, pollType: PollType) {
        buttons.add(KeyboardButton(text, requestPoll = KeyboardButtonPollType(pollType.type)))
    }

    fun requestUsers(text: String, requestId: Int, isBot: Boolean? = null, isPremium: Boolean? = null, maxQuantity: Int? = null, requestName: Boolean? = null, requestUsername: Boolean? = null, requestPhoto: Boolean? = null) {
        buttons.add(KeyboardButton(text, requestUsers = KeyboardButtonRequestUsers(requestId, isBot, isPremium, maxQuantity, requestName, requestUsername, requestPhoto)))
    }

    fun webAppButton(text: String, webApp: WebAppInfo) {
        buttons.add(KeyboardButton(text, webApp = webApp))
    }

    fun build(): List<KeyboardButton> {
        return buttons
    }
}


class ReplyKeyboardEnvironment() {
    private val rows: MutableList<List<KeyboardButton>> = mutableListOf()

    fun row(content: ReplyKeyboardRowEnvironment.() -> Unit) {
        val environment = ReplyKeyboardRowEnvironment()
        content(environment)
        rows.add(environment.build())
    }

    fun build(isPersistent: Boolean? = null, resizeKeyboard: Boolean? = null, oneTimeKeyboard: Boolean? = null, inputFieldPlaceholder: String? = null, selective: Boolean? = null): ReplyKeyboardMarkup {
        return ReplyKeyboardMarkup(rows, isPersistent, resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective)
    }
}

fun replyKeyboard(inputFieldPlaceholder: String? = null, isPersistent: Boolean? = null, resizeKeyboard: Boolean? = true, oneTimeKeyboard: Boolean? = true, selective: Boolean? = null, content: ReplyKeyboardEnvironment.() -> Unit): ReplyKeyboardMarkup {
    val environment = ReplyKeyboardEnvironment()
    content(environment)

    return environment.build(isPersistent, resizeKeyboard, oneTimeKeyboard, inputFieldPlaceholder, selective)
}