import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a bot command.
 *
 * @param command Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and underscores.
 * @param description Description of the command; 1-256 characters.
 */
@Serializable
class BotCommand(
    @SerialName("command") val command: String,
    @SerialName("description") val description: String,
) : TelegramObject()