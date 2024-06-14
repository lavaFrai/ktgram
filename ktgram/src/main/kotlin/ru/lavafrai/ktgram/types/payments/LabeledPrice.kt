package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject


@Serializable
class LabeledPrice(
    @SerialName("label") val label: String,
    @SerialName("amount") val amount: Int,
): TelegramObject()


fun simplePrice(amount: Int, label: String = "price") = listOf(LabeledPrice(label, amount))