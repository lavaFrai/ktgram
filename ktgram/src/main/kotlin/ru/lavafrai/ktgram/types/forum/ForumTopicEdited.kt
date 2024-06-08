package ru.lavafrai.ktgram.types.forum

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about an edited forum topic.
 *
 * @param name *Optional.* New name of the topic, if it was edited
 * @param iconCustomEmojiId *Optional.* New identifier of the custom emoji shown as the topic icon, if it was edited; an empty string if the icon was removed
 */
@Serializable
class ForumTopicEdited(
    @SerialName("name") val name: String? = null,
    @SerialName("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
) : TelegramObject()