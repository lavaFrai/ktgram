package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to an animated GIF file stored on the Telegram servers. By default, this animated GIF file will be sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with specified content instead of the animation.
 *
 * @param type Type of the result, must be gif
 * @param id Unique identifier for this result, 1-64 bytes
 * @param gifFileId A valid file identifier for the GIF file
 * @param title Title for the result
 * @param caption Caption of the GIF file to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the GIF animation
 */
@Serializable
class InlineQueryResultCachedGif(
    @SerialName("type") val type: String = "gif",
    @SerialName("id") val id: String,
    @SerialName("gif_file_id") val gifFileId: String,
    @SerialName("title") val title: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media") val showCaptionAboveMedia: Boolean? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}