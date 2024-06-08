import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.ReplyMarkup

/**
 * Upon receiving a message with this object, Telegram clients will remove the current custom keyboard and display the default letter-keyboard. By default, custom keyboards are displayed until a new keyboard is sent by a bot. An exception is made for one-time keyboards that are hidden immediately after the user presses a button (see [ReplyKeyboardMarkup][ru.lavafrai.ktgram.types.ReplyKeyboardMarkup]). Not supported in channels and for messages sent on behalf of a Telegram Business account.
 *
 * @param removeKeyboard Requests clients to remove the custom keyboard (user will not be able to summon this keyboard; if you want to hide the keyboard from sight but keep it accessible, use one_time_keyboard in [ReplyKeyboardMarkup][ru.lavafrai.ktgram.types.ReplyKeyboardMarkup])
 * @param selective *Optional.* Use this parameter if you want to remove the keyboard for specific users only. Targets: 1) users that are @mentioned in the text of the [Message][ru.lavafrai.ktgram.types.Message] object; 2) if the bot's message is a reply to a message in the same chat and forum topic, sender of the original message.
 *
 * Example: A user votes in a poll, bot returns confirmation message in reply to the vote and removes the keyboard for that user, while still showing the keyboard with poll options to users who haven't voted yet.
 */
@Serializable
class ReplyKeyboardRemove(
    @SerialName("remove_keyboard") val removeKeyboard: Boolean,
    @SerialName("selective") val selective: Boolean? = null,
) : ReplyMarkup()