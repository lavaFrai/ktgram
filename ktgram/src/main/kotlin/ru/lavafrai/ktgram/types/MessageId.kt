import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a unique message identifier.
 *
 * @param messageId Unique message identifier
 */
@Serializable
class MessageId(
    @SerialName("message_id") val messageId: Int,
) : TelegramObject()