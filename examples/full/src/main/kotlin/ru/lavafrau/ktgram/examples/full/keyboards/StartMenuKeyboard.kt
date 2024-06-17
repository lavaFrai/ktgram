package ru.lavafrau.ktgram.examples.full.keyboards

import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard

val startMenuKeyboard = inlineKeyboard {
    row {
        button("Messages", "demo.messages")
        button("Keyboards", "demo.keyboard")
    }
}