package ru.lavafrai.ktgram.types.forum

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a new forum topic created in the chat.
 *
 * @param name Name of the topic
 * @param iconColor Color of the topic icon in RGB format
 * @param iconCustomEmojiId *Optional.* Unique identifier of the custom emoji shown as the topic icon
 */
@Serializable
class ForumTopicCreated(
    @SerialName("name") val name: String,
    @SerialName("icon_color") val iconColor: Int,
    @SerialName("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
) : TelegramObject()