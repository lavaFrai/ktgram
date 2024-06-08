package ktgram.types.stickers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.PhotoSize

/**
 * This object represents a sticker set.
 *
 * @param name Sticker set name
 * @param title Sticker set title
 * @param stickerType Type of stickers in the set, currently one of “regular”, “mask”, “custom_emoji”
 * @param stickers List of all set stickers
 * @param thumbnail Optional. Sticker set thumbnail in the .WEBP, .TGS, or .WEBM format
 */
@Serializable
class StickerSet(
    @SerialName("name") val name: String,
    @SerialName("title") val title: String,
    @SerialName("sticker_type") val stickerType: String,
    @SerialName("stickers") val stickers: List<Sticker>,
    @SerialName("thumbnail") val thumbnail: PhotoSize? = null,
) : TelegramObject()