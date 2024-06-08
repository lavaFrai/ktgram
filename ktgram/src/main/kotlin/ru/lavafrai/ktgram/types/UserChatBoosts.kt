import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a list of boosts added to a chat by a user.
 *
 * @param boosts The list of boosts added to the chat by the user
 */
@Serializable
class UserChatBoosts(
    @SerialName("boosts") val boosts: List<ChatBoost>,
) : TelegramObject()