package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

@Serializable
class ShippingOption(
    val id: String,
    val title: String,
    val prices: List<LabeledPrice>,
): TelegramObject() {
}