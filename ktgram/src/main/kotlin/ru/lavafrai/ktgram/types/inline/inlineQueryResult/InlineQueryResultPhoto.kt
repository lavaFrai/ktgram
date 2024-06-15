package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a photo. By default, this photo will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the photo.
 *
 * @param type Type of the result, must be photo
 * @param id Unique identifier for this result, 1-64 bytes
 * @param photoUrl A valid URL of the photo. Photo must be in JPEG format. Photo size must not exceed 5MB
 * @param thumbnailUrl URL of the thumbnail for the photo
 * @param photoWidth Width of the photo
 * @param photoHeight Height of the photo
 * @param title Title for the result
 * @param description Short description of the result
 * @param caption Caption of the photo to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the photo caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the photo
 */
@Serializable
class InlineQueryResultPhoto(
    @SerialName("type") val type: String = "photo",
    @SerialName("id") val id: String,
    @SerialName("photo_url") val photoUrl: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("photo_width") val photoWidth: Int? = null,
    @SerialName("photo_height") val photoHeight: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media") val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): InlineQueryResult() {
}