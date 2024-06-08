import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents the content of a service message, sent whenever a user in the chat triggers a proximity alert set by another user.
 *
 * @param traveler User that triggered the alert
 * @param watcher User that set the alert
 * @param distance The distance between the users
 */
@Serializable
class ProximityAlertTriggered(
    @SerialName("traveler") val traveler: User,
    @SerialName("watcher") val watcher: User,
    @SerialName("distance") val distance: Int,
) : TelegramObject()