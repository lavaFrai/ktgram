import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.PhotoSize

/**
 * This object contains information about a user that was shared with the bot using a KeyboardButtonRequestUsers button.
 *
 * @param userId Identifier of the shared user. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so 64-bit integers or double-precision float types are safe for storing these identifiers. The bot may not have access to the user and could be unable to use this identifier, unless the user is already known to the bot by some other means.
 * @param firstName *Optional.* First name of the user, if the name was requested by the bot
 * @param lastName *Optional.* Last name of the user, if the name was requested by the bot
 * @param username *Optional.* Username of the user, if the username was requested by the bot
 * @param photo
 */
@Serializable
class SharedUser(
    @SerialName("user_id") val userId: Long,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("photo") val photo: List<PhotoSize>? = null,
) : TelegramObject()