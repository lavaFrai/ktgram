import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes data sent from a Web App to the bot.
 *
 * @param data The data. Be aware that a bad client can send arbitrary data in this field.
 * @param buttonText Text of the web_app keyboard button from which the Web App was opened. Be aware that a bad client can send arbitrary data in this field.
 */
@Serializable
class WebAppData(
    @SerialName("data") val data: String,
    @SerialName("button_text") val buttonText: String? = null,
) : TelegramObject()