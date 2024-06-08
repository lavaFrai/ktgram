import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents a change of a reaction on a message performed by a user.
 *
 * @param chat The chat containing the message the user reacted to
 * @param messageId Unique identifier of the message inside the chat
 * @param user *Optional.* The user that changed the reaction, if the user isn't anonymous
 * @param actorChat *Optional.* The chat on behalf of which the reaction was changed, if the user is anonymous
 * @param date Date of the change in Unix time
 * @param oldReaction Previous list of reaction types that were set by the user
 * @param newReaction New list of reaction types that have been set by the user
 */
@Serializable
class MessageReactionUpdated(
    @SerialName("chat") val chat: Chat,
    @SerialName("message_id") val messageId: Int,
    @SerialName("user") val user: User? = null,
    @SerialName("actor_chat") val actorChat: Chat? = null,
    @SerialName("date") val date: Int,
    @SerialName("old_reaction") val oldReaction: List<ReactionType>,
    @SerialName("new_reaction") val newReaction: List<ReactionType>,
) : TelegramObject()