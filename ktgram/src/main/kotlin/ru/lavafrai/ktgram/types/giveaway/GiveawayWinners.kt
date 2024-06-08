package ru.lavafrai.ktgram.types.giveaway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents a message about the completion of a giveaway with public winners.
 *
 * @param chat The chat that created the giveaway
 * @param giveawayMessageId Identifier of the message with the giveaway in the chat
 * @param winnersSelectionDate Point in time (Unix timestamp) when winners of the giveaway were selected
 * @param winnerCount Total number of winners in the giveaway
 * @param winners List of up to 100 winners of the giveaway
 * @param additionalChatCount *Optional.* The number of other chats the user had to join in order to be eligible for the giveaway
 * @param premiumSubscriptionMonthCount *Optional.* The number of months the Telegram Premium subscription won from the giveaway will be active for
 * @param unclaimedPrizeCount *Optional.* Number of undistributed prizes
 * @param onlyNewMembers *Optional.* True, if only users who had joined the chats after the giveaway started were eligible to win
 * @param wasRefunded *Optional.* True, if the giveaway was canceled because the payment for it was refunded
 * @param prizeDescription *Optional.* Description of additional giveaway prize
 */
@Serializable
class GiveawayWinners(
    @SerialName("chat") val chat: Chat,
    @SerialName("giveaway_message_id") val giveawayMessageId: Int,
    @SerialName("winners_selection_date") val winnersSelectionDate: Int,
    @SerialName("winner_count") val winnerCount: Int,
    @SerialName("winners") val winners: List<User>,
    @SerialName("additional_chat_count") val additionalChatCount: Int? = null,
    @SerialName("premium_subscription_month_count") val premiumSubscriptionMonthCount: Int? = null,
    @SerialName("unclaimed_prize_count") val unclaimedPrizeCount: Int? = null,
    @SerialName("only_new_members") val onlyNewMembers: Boolean? = null,
    @SerialName("was_refunded") val wasRefunded: Boolean? = null,
    @SerialName("prize_description") val prizeDescription: String? = null,
) : TelegramObject()