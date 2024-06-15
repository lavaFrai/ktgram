package ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard

import LoginUrl
import WebAppData
import WebAppInfo

class InlineKeyboardRowEnvironment() {
    private val buttons: MutableList<InlineKeyboardButton> = mutableListOf()

    fun button(text: String, data: String) {
        buttons.add(InlineKeyboardButton(text, callbackData=data))
    }

    fun urlButton(text: String, url: String) {
        buttons.add(InlineKeyboardButton(text, url=url))
    }

    fun loginButton(text: String, loginUrl: LoginUrl) {
        buttons.add(InlineKeyboardButton(text, loginUrl=loginUrl))
    }

    fun webAppButton(text: String, webAppData: WebAppInfo) {
        buttons.add(InlineKeyboardButton(text, webApp=webAppData))
    }

    fun build(): List<InlineKeyboardButton> {
        return buttons
    }
}

class InlineKeyboardEnvironment() {
    private val rows: MutableList<List<InlineKeyboardButton>> = mutableListOf()

    fun row(content: InlineKeyboardRowEnvironment.() -> Unit) {
        val environment = InlineKeyboardRowEnvironment()
        content(environment)
        rows.add(environment.build())
    }

    fun button(text: String, data: String) {
        row { button(text, data=data) }
    }

    fun urlButton(text: String, url: String) {
        row { urlButton(text, url) }
    }

    fun loginButton(text: String, loginUrl: LoginUrl) {
        row { loginButton(text, loginUrl) }
    }

    fun webAppButton(text: String, webAppData: WebAppInfo) {
        row { webAppButton(text, webAppData) }
    }

    fun build(): InlineKeyboardMarkup {
        return InlineKeyboardMarkup(rows)
    }
}

fun inlineKeyboard(content: InlineKeyboardEnvironment.() -> Unit): InlineKeyboardMarkup {
    val environment = InlineKeyboardEnvironment()
    content(environment)

    return environment.build()
}

fun removeInlineKeyboard() = inlineKeyboard {}