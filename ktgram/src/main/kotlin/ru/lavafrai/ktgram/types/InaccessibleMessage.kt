import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object describes a message that was deleted or is otherwise inaccessible to the bot.
 *
 * @param chat Chat the message belonged to
 * @param messageId Unique message identifier inside the chat
 * @param date Always 0. The field can be used to differentiate regular and inaccessible messages.
 */
@Serializable
class InaccessibleMessage(
    @SerialName("chat") val chat: Chat,
    @SerialName("message_id") val messageId: Int,
    @SerialName("date") val date: Int,
) : TelegramObject()