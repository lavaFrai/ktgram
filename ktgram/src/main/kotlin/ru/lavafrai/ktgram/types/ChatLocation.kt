import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.Location

/**
 * Represents a location to which a chat is connected.
 *
 * @param location The location to which the supergroup is connected. Can't be a live location.
 * @param address Location address; 1-64 characters, as defined by the chat owner
 */
@Serializable
class ChatLocation(
    @SerialName("location") val location: Location,
    @SerialName("address") val address: String,
) : TelegramObject()