package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a file stored on the Telegram servers. By default, this file will be sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the file.
 *
 * @param type Type of the result, must be document
 * @param id Unique identifier for this result, 1-64 bytes
 * @param title Title for the result
 * @param documentFileId A valid file identifier for the file
 * @param description Short description of the result
 * @param caption Caption of the document to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the document caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the file
 */
@Serializable
class InlineQueryResultCachedDocument(
    @SerialName("type") val type: String = "document",
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("document_file_id") val documentFileId: String,
    @SerialName("description") val description: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}