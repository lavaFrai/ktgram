package ru.lavafrai.ktgrammediagroup

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.message
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.inputmedia.InputMediaType
import ru.lavafrai.ktgram.types.media.inputmedia.toMedia

fun Router.addHandlers() {
    message {
        handle {
            val urls = listOf(
                "https://randomwordgenerator.com/img/picture-generator/51e3d4424d53b10ff3d8992cc12c30771037dbf85254784b7d297ed7934b_640.jpg",
                "https://randomwordgenerator.com/img/picture-generator/54e4d7434256a414f1dc8460962e33791c3ad6e04e5074417c2f7dd59748c3_640.jpg",
                "https://randomwordgenerator.com/img/picture-generator/57e5dd4b4b52a414f1dc8460962e33791c3ad6e04e5074417c2f7dd49f44c0_640.jpg",
                "https://randomwordgenerator.com/img/picture-generator/57e1d4454f5aaa14f1dc8460962e33791c3ad6e04e507441722a72dd934ec3_640.jpg",
            )

            val images = urls.map { InputFile.fromURL(it).toMedia(InputMediaType.PHOTO) }

            update.message!!.answerMediaGroup(images)
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