import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a video chat ended in the chat.
 *
 * @param duration ru.lavafrai.ktgram.types.media.Video chat duration in seconds
 */
@Serializable
class VideoChatEnded(
    @SerialName("duration") val duration: Int,
) : TelegramObject()