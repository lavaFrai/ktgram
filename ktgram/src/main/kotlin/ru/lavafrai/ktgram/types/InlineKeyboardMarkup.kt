import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.ReplyMarkup

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 *
 * @param inlineKeyboard Array of button rows, each represented by an Array of [InlineKeyboardButton][ru.lavafrai.ktgram.types.InlineKeyboardButton] objects
 */
@Serializable
class InlineKeyboardMarkup(
    @SerialName("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyMarkup()