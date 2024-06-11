package ru.lavafrai.ktgramfiles

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.message
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType
import ru.lavafrai.ktgram.types.inputfile.InputFile

fun Dispatcher.addHandlers() {
    text {
        val demo1 = InputFile.fromResource("kotlin.png")
        message.answerPhoto(demo1)
    }

    photo {
        val photoFileId = message.photo!!.last().fileId
        val photoFileBytes = bot.downloadFileToBytes(photoFileId)
        val demo2 = InputFile.fromBytes(photoFileBytes)
        message.answerPhoto(demo2)
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