package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User


@Serializable
class PreCheckoutQuery(
    @SerialName("id") val id: String,
    @SerialName("from") val from: User,
    @SerialName("currency") val currency: String,
    @SerialName("total_amount") val totalAmount: Int,
    @SerialName("invoice_payload") val invoicePayload: String,
    @SerialName("shipping_option_id") val shippingOptionId: String? = null,
    @SerialName("order_info") val orderInfo: OrderInfo? = null,
): TelegramObject() {
    suspend fun answer(
        ok: Boolean,
        errorMessage: String? = null,
    ) = bot.api.answerPreCheckoutQuery(id, ok, errorMessage)
}