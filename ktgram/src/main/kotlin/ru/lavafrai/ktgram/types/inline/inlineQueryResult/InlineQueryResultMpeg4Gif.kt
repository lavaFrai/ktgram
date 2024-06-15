package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this animated MPEG-4 file will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the animation.
 *
 * @param type Type of the result, must be mpeg4_gif
 * @param id Unique identifier for this result, 1-64 bytes
 * @param mpeg4Url A valid URL for the MPEG4 file. File size must not exceed 1MB
 * @param mpeg4Width Video width
 * @param mpeg4Height Video height
 * @param mpeg4Duration Video duration in seconds
 * @param thumbnailUrl URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
 * @param thumbnailMimeType MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
 * @param title Title for the result
 * @param caption Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the video animation
 */
@Serializable
class InlineQueryResultMpeg4Gif(
    @SerialName("type") val type: String = "mpeg4_gif",
    @SerialName("id") val id: String,
    @SerialName("mpeg4_url") val mpeg4Url: String,
    @SerialName("mpeg4_width") val mpeg4Width: Int? = null,
    @SerialName("mpeg4_height") val mpeg4Height: Int? = null,
    @SerialName("mpeg4_duration") val mpeg4Duration: Int? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("thumbnail_mime_type") val thumbnailMimeType: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media") val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}