import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.ChatBoostSource
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a boost removed from a chat.
 *
 * @param chat Chat which was boosted
 * @param boostId Unique identifier of the boost
 * @param removeDate Point in time (Unix timestamp) when the boost was removed
 * @param source Source of the removed boost
 */
@Serializable
class ChatBoostRemoved(
    @SerialName("chat") val chat: Chat,
    @SerialName("boost_id") val boostId: String,
    @SerialName("remove_date") val removeDate: Int,
    @SerialName("source") val source: ChatBoostSource,
) : TelegramObject()