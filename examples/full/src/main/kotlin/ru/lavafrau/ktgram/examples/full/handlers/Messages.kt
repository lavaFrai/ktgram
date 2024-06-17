package ru.lavafrau.ktgram.examples.full.handlers

import ru.lavafrai.ktgram.dispatcher.Router
import ru.lavafrai.ktgram.dispatcher.callbackQuery
import ru.lavafrai.ktgram.dispatcher.handle
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.DiceEmoji
import ru.lavafrai.ktgram.types.media.inputmedia.InputMediaType
import ru.lavafrai.ktgram.types.media.inputmedia.toMedia
import ru.lavafrau.ktgram.examples.full.keyboards.messageTypeSelectKeyboard

fun Router<*>.messagesHandler() {
    callbackQuery("demo.messages") {
        handle {
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
            callbackQuery.message!!.delete()
        }
    }

    callbackQuery("demo.message.text") {
        handle {
            callbackQuery.message!!.answer("This is a simple text message.")
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.photo") {
        handle {
            callbackQuery.message!!.answerPhoto(InputFile.fromURL("https://filesamples.com/samples/image/png/sample_1920%C3%971280.png"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.audio") {
        handle {
            callbackQuery.message!!.answerAudio(InputFile.fromURL("https://filesamples.com/samples/audio/mp3/Symphony%20No.6%20(1st%20movement).mp3"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.document") {
        handle {
            callbackQuery.message!!.answerDocument(InputFile.fromURL("https://filesamples.com/samples/document/pdf/sample3.pdf"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.video") {
        handle {
            callbackQuery.message!!.answerVideo(InputFile.fromURL("https://filesamples.com/samples/video/mp4/sample_1280x720.mp4"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.animation") {
        handle {
            callbackQuery.message!!.answerAnimation(InputFile.fromURL("https://filesamples.com/samples/video/mp4/sample_1280x720.mp4"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.voice") {
        handle {
            callbackQuery.message!!.answerVoice(InputFile.fromResource("sample.mp3"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.video_note") {
        handle {
            callbackQuery.message!!.answerVideoNote(InputFile.fromResource("video_note.mp4"))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.media_group") {
        handle {
            callbackQuery.message!!.answerMediaGroup(listOf(
                InputFile.fromURL("https://filesamples.com/samples/image/png/sample_1920%C3%971280.png").toMedia(InputMediaType.PHOTO),
                InputFile.fromURL("https://filesamples.com/samples/video/mp4/sample_1280x720.mp4").toMedia(InputMediaType.VIDEO),
            ))
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer("How message type which you would like to see.", replyMarkup = messageTypeSelectKeyboard)
        }
    }

    callbackQuery("demo.message.location") {
        handle {
            callbackQuery.message!!.answerLocation(55.7558f, 37.6176f)
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer(
                "How message type which you would like to see.",
                replyMarkup = messageTypeSelectKeyboard
            )
        }
    }

    callbackQuery("demo.message.venue") {
        handle {
            callbackQuery.message!!.answerVenue(55.754017f, 37.62038f, "Moscow", "Red Square")
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer(
                "How message type which you would like to see.",
                replyMarkup = messageTypeSelectKeyboard
            )
        }
    }

    callbackQuery("demo.message.contact") {
        handle {
            callbackQuery.message!!.answerContact("+1234567890", "John", "Doe")
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer(
                "How message type which you would like to see.",
                replyMarkup = messageTypeSelectKeyboard
            )
        }
    }

    callbackQuery("demo.message.poll") {
        handle {
            callbackQuery.message!!.answerPoll(
                "What is your favorite programming language?",
                listOf("Kotlin", "Java", "Python", "JavaScript")
            )
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer(
                "How message type which you would like to see.",
                replyMarkup = messageTypeSelectKeyboard
            )
        }
    }

    callbackQuery("demo.message.dice") {
        handle {
            callbackQuery.message!!.answerDice(DiceEmoji.SLOT_MACHINE)
            callbackQuery.message!!.delete()
            callbackQuery.message!!.answer(
                "How message type which you would like to see.",
                replyMarkup = messageTypeSelectKeyboard
            )
        }
    }
}
