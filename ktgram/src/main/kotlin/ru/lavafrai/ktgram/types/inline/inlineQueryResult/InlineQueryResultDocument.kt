package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a file. By default, this file will be sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the file. Currently, only .PDF and .ZIP files can be sent using this method.
 *
 * @param type Type of the result, must be document
 * @param id Unique identifier for this result, 1-64 bytes
 * @param title Title for the result
 * @param caption Caption of the document to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the document caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param documentUrl A valid URL for the file
 * @param mimeType MIME type of the content of the file, either “application/pdf” or “application/zip”
 * @param description Short description of the result
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the file
 * @param thumbnailUrl URL of the thumbnail (JPEG only) for the file
 * @param thumbnailWidth Thumbnail width
 * @param thumbnailHeight Thumbnail height
 */
@Serializable
class InlineQueryResultDocument(
    @SerialName("type") val type: String = "document",
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("document_url") val documentUrl: String,
    @SerialName("mime_type") val mimeType: String,
    @SerialName("description") val description: String? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width") val thumbnailWidth: Int? = null,
    @SerialName("thumbnail_height") val thumbnailHeight: Int? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}