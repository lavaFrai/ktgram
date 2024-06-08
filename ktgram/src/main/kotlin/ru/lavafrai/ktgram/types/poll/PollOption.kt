package ru.lavafrai.ktgram.types.poll

import ru.lavafrai.ktgram.types.media.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object contains information about one answer option in a poll.
 *
 * @param text Option text, 1-100 characters
 * @param textEntities *Optional.* Special entities that appear in the option text. Currently, only custom emoji entities are allowed in poll option texts
 * @param voterCount Number of users that voted for this option
 */
@Serializable
class PollOption(
    @SerialName("text") val text: String,
    @SerialName("text_entities") val textEntities: List<MessageEntity>? = null,
    @SerialName("voter_count") val voterCount: Int,
) : TelegramObject()