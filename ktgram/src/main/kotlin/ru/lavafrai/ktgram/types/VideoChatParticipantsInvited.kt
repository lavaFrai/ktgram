import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents a service message about new members invited to a video chat.
 *
 * @param users New members that were invited to the video chat
 */
@Serializable
class VideoChatParticipantsInvited(
    @SerialName("users") val users: List<User>,
) : TelegramObject()