import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a boost added to a chat or changed.
 *
 * @param chat Chat which was boosted
 * @param boost Information about the chat boost
 */
@Serializable
class ChatBoostUpdated(
    @SerialName("chat") val chat: Chat,
    @SerialName("boost") val boost: ChatBoost,
) : TelegramObject()