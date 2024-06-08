package ktgram.types.stickers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.File
import ru.lavafrai.ktgram.types.media.PhotoSize

/**
 * This object represents a sticker.
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
 * @param type Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. The type of the sticker is independent from its format, which is determined by the fields is_animated and is_video.
 * @param width Sticker width
 * @param height Sticker height
 * @param isAnimated True, if the sticker is animated
 * @param isVideo True, if the sticker is a video sticker
 * @param thumbnail Optional. Sticker thumbnail in the .WEBP or .JPG format
 * @param emoji Optional. Emoji associated with the sticker
 * @param setName Optional. Name of the sticker set to which the sticker belongs
 * @param premiumAnimation Optional. For premium regular stickers, premium animation for the sticker
 * @param maskPosition Optional. For mask stickers, the position where the mask should be placed
 * @param customEmojiId Optional. For custom emoji stickers, unique identifier of the custom emoji
 * @param needsRepainting Optional. True, if the sticker must be repainted to a text color in messages, the color of the Telegram Premium badge in emoji status, white color on chat photos, or another appropriate color in other places
 * @param fileSize Optional. File size in bytes
 */
@Serializable
class Sticker(
    @SerialName("file_id") val fileId: String,
    @SerialName("file_unique_id") val fileUniqueId: String,
    @SerialName("type") val type: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("is_animated") val isAnimated: Boolean,
    @SerialName("is_video") val isVideo: Boolean,
    @SerialName("thumbnail") val thumbnail: PhotoSize? = null,
    @SerialName("emoji") val emoji: String? = null,
    @SerialName("set_name") val setName: String? = null,
    @SerialName("premium_animation") val premiumAnimation: File? = null,
    @SerialName("mask_position") val maskPosition: MaskPosition? = null,
    @SerialName("custom_emoji_id") val customEmojiId: String? = null,
    @SerialName("needs_repainting") val needsRepainting: Boolean? = null,
    @SerialName("file_size") val fileSize: Int? = null,
) : TelegramObject()