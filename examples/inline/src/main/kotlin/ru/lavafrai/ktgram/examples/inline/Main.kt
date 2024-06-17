package ru.lavafrai.ktgram.examples.inline

import kotlinx.serialization.encodeToString
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResultVenue
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.answer
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputInvoiceMessageContent
import ru.lavafrai.ktgram.types.payments.simplePrice
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.inlineKeyboard


val inlineKeyboard = inlineKeyboard {
    row {
        urlButton("Google", "https://google.com")
        urlButton("Yandex", "https://yandex.ru")
    }

    button("Smth else", "nothing")
}

fun Router<*>.addHandlers() {
    inlineQuery {
        handle {
            val invoice = InputInvoiceMessageContent(
                title = "Cake",
                description = "Really awesome cake",
                payload = "cake",
                currency = "XTR",
                prices = simplePrice(1, "cake"),
            )

            inlineQuery.answer {
                location(55.7558f, 37.6176f, "Moscow", inputMessageContent = invoice)
                photo(
                    "https://filesamples.com/samples/image/jpeg/sample_1920%C3%971280.jpeg",
                    "https://filesamples.com/samples/image/jpeg/sample_1920%C3%971280.jpeg",
                    inputMessageContent = invoice,
                    id = "ada"
                )
            }
        }
    }
}


fun main() {
    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"))

    bot.api.json.encodeToString(
        InlineQueryResultVenue(
            id = "1",
            latitude = 55.7558f,
            longitude = 37.6176f,
            title = "Moscow",
            address = "Moscow, Russia",
        )
    ).let(::println)

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        addHandlers()
    }

    bot.runPolling()
}