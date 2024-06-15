package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object contains information about an incoming shipping query.
 *
 * @param id Unique query identifier
 * @param from User who sent the query
 * @param invoicePayload Bot specified invoice payload
 * @param shippingAddress User specified shipping address
 */
@Serializable
class ShippingQuery(
    @SerialName("id") val id: String,
    @SerialName("from") val from: User,
    @SerialName("invoice_payload") val invoicePayload: String,
    @SerialName("shipping_address") val shippingAddress: ShippingAddress,
): TelegramObject() {
}