import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents the bot's name.
 *
 * @param name The bot's name
 */
@Serializable
class BotName(
    @SerialName("name") val name: String,
) : TelegramObject()