package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

@Serializable
class ShippingAddress(
    @SerialName("country_code") val countryCode: String,
    @SerialName("state") val state: String,
    @SerialName("city") val city: String,
    @SerialName("street_line1") val streetLine1: String,
    @SerialName("street_line2") val streetLine2: String,
    @SerialName("post_code") val postCode: String,
): TelegramObject()