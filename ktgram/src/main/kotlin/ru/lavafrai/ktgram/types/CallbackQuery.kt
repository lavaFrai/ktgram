import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents an incoming callback query from a callback button in an inline keyboard. If the button that originated the query was attached to a message sent by the bot, the field message will be present. If the button was attached to a message sent via the bot (in inline mode), the field inline_message_id will be present. Exactly one of the fields data or game_short_name will be present.
 *
 * @param id Unique identifier for this query
 * @param from Sender
 * @param message *Optional.* Message sent by the bot with the callback button that originated the query
 * @param inlineMessageId *Optional.* Identifier of the message sent via the bot in inline mode, that originated the query.
 * @param chatInstance Global identifier, uniquely corresponding to the chat to which the message with the callback button was sent. Useful for high scores in games.
 * @param data *Optional.* Data associated with the callback button. Be aware that the message originated the query can contain no callback buttons with this data.
 * @param gameShortName *Optional.* Short name of a Game to be returned, serves as the unique identifier for the game
 *
 * NOTE: After the user presses a callback button, Telegram clients will display a progress bar until you call answerCallbackQuery. It is, therefore, necessary to react by calling answerCallbackQuery even if no notification to the user is needed (e.g., without specifying any of the optional parameters).
 */
@Serializable
class CallbackQuery(
    @SerialName("id") val id: String,
    @SerialName("from") val from: User,
    @SerialName("message") val message: Message? = null,
    @SerialName("inline_message_id") val inlineMessageId: String? = null,
    @SerialName("chat_instance") val chatInstance: String,
    @SerialName("data") val data: String? = null,
    @SerialName("game_short_name") val gameShortName: String? = null,
) : TelegramObject()