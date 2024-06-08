package ru.lavafrai.ktgram.types.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ktgram.types.stickers.Sticker
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Contains information about the start page settings of a Telegram Business account.
 *
 * @param title *Optional.* Title text of the business intro
 * @param message *Optional.* Message text of the business intro
 * @param sticker *Optional.* Sticker of the business intro
 */
@Serializable
class BusinessIntro(
    @SerialName("title") val title: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("sticker") val sticker: Sticker? = null,
) : TelegramObject()