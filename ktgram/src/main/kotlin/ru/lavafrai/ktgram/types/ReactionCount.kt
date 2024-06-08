import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Represents a reaction added to a message along with the number of times it was added.
 *
 * @param type Type of the reaction
 * @param totalCount Number of times the reaction was added
 */
@Serializable
class ReactionCount(
    @SerialName("type") val type: ReactionType,
    @SerialName("total_count") val totalCount: Int,
) : TelegramObject()