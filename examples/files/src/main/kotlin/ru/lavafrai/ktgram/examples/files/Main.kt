package ru.lavafrai.ktgram.examples.files

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.types.inputfile.InputFile

fun Router.addHandlers() {
    text {
        handle {
            val demo = InputFile.fromResource("kotlin.png")
            update.message!!.answerPhoto(demo)
        }
    }

    photo {
        handle {
            val photoFileId = update.message!!.photo!!.last().fileId
            val photoFileBytes = bot.downloadFileToBytes(photoFileId)

            val demo = InputFile.fromBytes(photoFileBytes)
            update.message!!.answerPhoto(demo)
        }
    }
}


fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        addHandlers()
    }

    bot.runPolling()
}