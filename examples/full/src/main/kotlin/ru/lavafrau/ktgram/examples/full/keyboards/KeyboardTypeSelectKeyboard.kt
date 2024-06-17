package ru.lavafrau.ktgram.examples.full.keyboards

import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard

val keyboardTypeSelectKeyboard = inlineKeyboard {
    row {
        button("Inline", "demo.keyboard.inline")
        button("Reply", "demo.keyboard.reply")
    }

    button("Clear keyboard", "demo.keyboard.clear")
    button("<- Back", "demo")
}