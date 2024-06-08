import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a video chat scheduled in the chat.
 *
 * @param startDate Point in time (Unix timestamp) when the video chat is supposed to be started by a chat administrator
 */
@Serializable
class VideoChatScheduled(
    @SerialName("start_date") val startDate: Int,
) : TelegramObject()