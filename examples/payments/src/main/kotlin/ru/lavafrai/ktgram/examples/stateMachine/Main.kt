package ru.lavafrai.ktgram.examples.stateMachine

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.*
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.types.payments.simplePrice

fun Router.addHandlers() {
    command("start") {
        handle {
            update.message!!.answerInvoice(
                "cake",
                "Delicious cake",
                "cake",
                "XTR",
                simplePrice(1, "cake"),
            )
        }
    }

    command("refund") {
        handle {
            val message = update.message!!

            val id = message.text!!.split(" ").getOrNull(1)
            id ?: run {
                message.answer("Please provide payment id.")
                return@handle
            }

            try {
                bot.api.refundStarsPayment(message.from!!.id, id)
            } catch (e: TelegramBadRequest) {
                message.answer("Refund failed. Wrong payment id.")
                return@handle
            }

            message.answer("Refund has been sent.")
        }
    }

    preCheckoutQuery {
        handle {
            update.preCheckoutQuery!!.answer(true)
        }
    }

    payment {
        handle {
            update.message!!.answer("Payment received. You have bought a cake for a ${update.message!!.successfulPayment!!.totalAmount} stars. Enjoy!")
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