package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a voice message stored on the Telegram servers. By default, this voice message will be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content instead of the voice message.
 *
 * @param type Type of the result, must be voice
 * @param id Unique identifier for this result, 1-64 bytes
 * @param voiceFileId A valid file identifier for the voice message
 * @param title Voice message title
 * @param caption Caption, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the voice message caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the voice message
 */
@Serializable
class InlineQueryResultCachedVoice(
    @SerialName("type") val type: String = "voice",
    @SerialName("id") val id: String,
    @SerialName("voice_file_id") val voiceFileId: String,
    @SerialName("title") val title: String,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}