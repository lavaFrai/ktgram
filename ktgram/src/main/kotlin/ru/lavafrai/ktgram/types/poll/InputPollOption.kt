package ru.lavafrai.ktgram.types.poll

import ru.lavafrai.ktgram.types.media.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object contains information about one answer option in a poll to send.
 *
 * @param text Option text, 1-100 characters
 * @param textParseMode *Optional.* Mode for parsing entities in the text. See formatting options for more details. Currently, only custom emoji entities are allowed
 * @param textEntities *Optional.* A JSON-serialized list of special entities that appear in the poll option text. It can be specified instead of text_parse_mode
 */
@Serializable
class InputPollOption(
    @SerialName("text") val text: String,
    @SerialName("text_parse_mode") val textParseMode: String? = null,
    @SerialName("text_entities") val textEntities: List<MessageEntity>? = null,
) : TelegramObject()