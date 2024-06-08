import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes the birthdate of a user.
 *
 * @param day Day of the user's birth; 1-31
 * @param month Month of the user's birth; 1-12
 * @param year *Optional.* Year of the user's birth
 */
@Serializable
class Birthdate(
    @SerialName("day") val day: Int,
    @SerialName("month") val month: Int,
    @SerialName("year") val year: Int? = null,
) : TelegramObject()