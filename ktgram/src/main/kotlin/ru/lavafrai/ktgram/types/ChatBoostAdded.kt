import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a user boosting a chat.
 *
 * @param boostCount Number of boosts added by the user
 */
@Serializable
class ChatBoostAdded(
    @SerialName("boost_count") val boostCount: Int,
) : TelegramObject()