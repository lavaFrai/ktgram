package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

@Serializable
class StarTransactions(
    @SerialName("transactions") val transactions: List<StarTransaction>,
): TelegramObject()