package ru.lavafrai.ktgram.types.giveaway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about the completion of a giveaway without public winners.
 *
 * @param winnerCount Number of winners in the giveaway
 * @param unclaimedPrizeCount *Optional.* Number of undistributed prizes
 * @param giveawayMessage *Optional.* Message with the giveaway that was completed, if it wasn't deleted
 */
@Serializable
class GiveawayCompleted(
    @SerialName("winner_count") val winnerCount: Int,
    @SerialName("unclaimed_prize_count") val unclaimedPrizeCount: Int? = null,
    @SerialName("giveaway_message") val giveawayMessage: Message? = null,
) : TelegramObject()