package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a sticker stored on the Telegram servers. By default, this sticker will be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content instead of the sticker.
 *
 * @param type Type of the result, must be sticker
 * @param id Unique identifier for this result, 1-64 bytes
 * @param stickerFileId A valid file identifier of the sticker
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the sticker
 */
@Serializable
class InlineQueryResultCachedSticker(
    @SerialName("type") val type: String = "sticker",
    @SerialName("id") val id: String,
    @SerialName("sticker_file_id") val stickerFileId: String,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}