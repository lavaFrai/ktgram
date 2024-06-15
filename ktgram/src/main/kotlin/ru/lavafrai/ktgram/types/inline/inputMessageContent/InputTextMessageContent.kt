package ru.lavafrai.ktgram.types.inline.inputMessageContent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity

/**
 * Represents the content of a text message to be sent as the result of an inline query.
 *
 * @param messageText Text of the message to be sent, 1-4096 characters
 * @param parseMode Mode for parsing entities in the message text. See formatting options for more details.
 * @param entities List of special entities that appear in message text, which can be specified instead of parse_mode
 * @param linkPreviewOptions Link preview generation options for the message
 */
@Serializable
class InputTextMessageContent(
    @SerialName("message_text") val messageText: String,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("entities") val entities: List<MessageEntity>? = null,
    @SerialName("link_preview") val linkPreviewOptions: LinkPreviewOptions? = null,
): InputMessageContent() {
}