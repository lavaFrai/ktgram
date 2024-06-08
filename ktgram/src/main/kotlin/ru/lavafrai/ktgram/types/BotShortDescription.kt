import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents the bot's short description.
 *
 * @param shortDescription The bot's short description
 */
@Serializable
class BotShortDescription(
    @SerialName("short_description") val shortDescription: String,
) : TelegramObject()