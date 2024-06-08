package ru.lavafrai.ktgram.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This class represents the source of a chat boost in Telegram.
 * It can be one of the following types:
 * - ChatBoostSourcePremium: The boost was obtained by subscribing to Telegram Premium or by gifting a Telegram Premium subscription to another user.
 * - ChatBoostSourceGiftCode: The boost was obtained by the creation of Telegram Premium gift codes to boost a chat. Each such code boosts the chat 4 times for the duration of the corresponding Telegram Premium subscription.
 * - ChatBoostSourceGiveaway: The boost was obtained by the creation of a Telegram Premium giveaway. This boosts the chat 4 times for the duration of the corresponding Telegram Premium subscription.
 *
 * @property source The source of the chat boost. Must be one of the following types: "premium", "gift_code", "giveaway".
 * @property giveawayMessageId ChatBoostSourceGiveaway only. The ID of the giveaway message. Identifier of a message in the chat with the giveaway; the message could have been deleted already. May be 0 if the message isn't sent yet.
 * @property user Optional in ChatBoostSourceGiveaway. The user who boosted the chat, if any. User that won the prize in the giveaway if any.
 * @property isUnclaimed ChatBoostSourceGiveaway only. Indicates whether the boost is unclaimed. True, if the giveaway was completed, but there was no user to win the prize.
 */
@Serializable
class ChatBoostSource (
    @SerialName("source") val source: String,
    @SerialName("giveaway_message_id") val giveawayMessageId: Int? = null,
    @SerialName("user") val user: User? = null,
    @SerialName("is_unclaimed") val isUnclaimed: Boolean? = null,
): TelegramObject()