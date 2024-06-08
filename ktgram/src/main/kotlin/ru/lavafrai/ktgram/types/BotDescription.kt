import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents the bot's description.
 *
 * @param description The bot's description
 */
@Serializable
class BotDescription(
    @SerialName("description") val description: String,
) : TelegramObject()