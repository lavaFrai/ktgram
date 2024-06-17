package ru.lavafrau.ktgram.examples.full.keyboards

import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard

val messageTypeSelectKeyboard = inlineKeyboard {
    row {
        button("Text", "demo.message.text")
        button("Photo", "demo.message.photo")
    }
    row {
        button("Audio", "demo.message.audio")
        button("Document", "demo.message.document")
    }
    row {
        button("Video", "demo.message.video")
        button("Animation", "demo.message.animation")
    }
    row {
        button("Voice", "demo.message.voice")
        button("Video note", "demo.message.video_note")
    }
    row {
        button("Media group", "demo.message.media_group")
        button("Location", "demo.message.location")
    }
    row {
        button("Venue", "demo.message.venue")
        button("Contact", "demo.message.contact")
    }
    row {
        button("Poll", "demo.message.poll")
        button("Dice", "demo.message.dice")
    }

    button("<- Back", "demo")
}