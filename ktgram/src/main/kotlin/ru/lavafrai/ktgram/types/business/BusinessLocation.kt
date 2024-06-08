package ru.lavafrai.ktgram.types.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.Location

/**
 * Contains information about the location of a Telegram Business account.
 *
 * @param address Address of the business
 * @param location *Optional.* Location of the business
 */
@Serializable
class BusinessLocation(
    @SerialName("address") val address: String,
    @SerialName("location") val location: Location? = null,
) : TelegramObject()