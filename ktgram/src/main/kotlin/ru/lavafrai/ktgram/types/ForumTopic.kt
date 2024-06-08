import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a forum topic.
 *
 * @param messageThreadId Unique identifier of the forum topic
 * @param name Name of the topic
 * @param iconColor Color of the topic icon in RGB format
 * @param iconCustomEmojiId *Optional.* Unique identifier of the custom emoji shown as the topic icon
 */
@Serializable
class ForumTopic(
    @SerialName("message_thread_id") val messageThreadId: Int,
    @SerialName("name") val name: String,
    @SerialName("icon_color") val iconColor: Int,
    @SerialName("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
) : TelegramObject()