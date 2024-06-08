package ru.lavafrai.ktgram.types.giveaway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a message about a scheduled giveaway.
 *
 * @param chats The list of chats which the user must join to participate in the giveaway
 * @param winnersSelectionDate Point in time (Unix timestamp) when winners of the giveaway will be selected
 * @param winnerCount The number of users which are supposed to be selected as winners of the giveaway
 * @param onlyNewMembers *Optional.* True, if only users who join the chats after the giveaway started should be eligible to win
 * @param hasPublicWinners *Optional.* True, if the list of giveaway winners will be visible to everyone
 * @param prizeDescription *Optional.* Description of additional giveaway prize
 * @param countryCodes *Optional.* A list of two-letter ISO 3166-1 alpha-2 country codes indicating the countries from which eligible users for the giveaway must come. If empty, then all users can participate in the giveaway. Users with a phone number that was bought on Fragment can always participate in giveaways.
 * @param premiumSubscriptionMonthCount *Optional.* The number of months the Telegram Premium subscription won from the giveaway will be active for
 */
@Serializable
class Giveaway(
    @SerialName("chats") val chats: List<Chat>,
    @SerialName("winners_selection_date") val winnersSelectionDate: Int,
    @SerialName("winner_count") val winnerCount: Int,
    @SerialName("only_new_members") val onlyNewMembers: Boolean? = null,
    @SerialName("has_public_winners") val hasPublicWinners: Boolean? = null,
    @SerialName("prize_description") val prizeDescription: String? = null,
    @SerialName("country_codes") val countryCodes: List<String>? = null,
    @SerialName("premium_subscription_month_count") val premiumSubscriptionMonthCount: Int? = null,
) : TelegramObject()