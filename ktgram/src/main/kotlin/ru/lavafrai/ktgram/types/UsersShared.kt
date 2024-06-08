import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object contains information about the users whose identifiers were shared with the bot using a KeyboardButtonRequestUsers button.
 *
 * @param requestId Identifier of the request
 * @param users Information about users shared with the bot.
 */
@Serializable
class UsersShared(
    @SerialName("request_id") val requestId: Int,
    @SerialName("users") val users: List<SharedUser>,
) : TelegramObject()