package ru.lavafrai.ktgram.examples.payments

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.dispatcher.message
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.payments.LabeledPrice
import ru.lavafrai.ktgram.types.payments.simplePrice

fun Dispatcher.addHandlers() {
    command("start") {
        message.answerInvoice(
            "cake",
            "Delicious cake",
            "cake",
            "XTR",
            simplePrice(1, "cake"),
        )
    }

    command("refund") {
        val id = message.text!!.split(" ").getOrNull(1)
        id ?: run {
            message.answer("Please provide payment id.")
            return@command
        }

        try {
            bot.api.refundStarsPayment(message.from!!.id, id)
        } catch (e: TelegramBadRequest) {
            message.answer("Refund failed. Wrong payment id.")
            return@command
        }

        message.answer("Refund has been sent.")
    }

    preCheckoutQuery {
        query.answer(true)
    }

    payment {
        message.answer("Payment received. You have bought a cake for a ${message.successfulPayment!!.totalAmount} stars. Enjoy!")
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