import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a change in auto-delete timer settings.
 *
 * @param messageAutoDeleteTime New auto-delete time for messages in the chat; in seconds
 */
@Serializable
class MessageAutoDeleteTimerChanged(
    @SerialName("message_auto_delete_time") val messageAutoDeleteTime: Int,
) : TelegramObject()