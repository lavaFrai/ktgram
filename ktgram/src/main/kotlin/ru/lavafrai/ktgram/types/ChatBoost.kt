import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.ChatBoostSource
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object contains information about a chat boost.
 *
 * @param boostId Unique identifier of the boost
 * @param addDate Point in time (Unix timestamp) when the chat was boosted
 * @param expirationDate Point in time (Unix timestamp) when the boost will automatically expire, unless the booster's Telegram Premium subscription is prolonged
 * @param source Source of the added boost
 */
@Serializable
class ChatBoost(
    @SerialName("boost_id") val boostId: String,
    @SerialName("add_date") val addDate: Int,
    @SerialName("expiration_date") val expirationDate: Int,
    @SerialName("source") val source: ChatBoostSource,
) : TelegramObject()