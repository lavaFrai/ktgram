import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents reaction changes on a message with anonymous reactions.
 *
 * @param chat The chat containing the message
 * @param messageId Unique message identifier inside the chat
 * @param date Date of the change in Unix time
 * @param reactions List of reactions that are present on the message
 */
@Serializable
class MessageReactionCountUpdated(
    @SerialName("chat") val chat: Chat,
    @SerialName("message_id") val messageId: Int,
    @SerialName("date") val date: Int,
    @SerialName("reactions") val reactions: List<ReactionCount>,
) : TelegramObject()