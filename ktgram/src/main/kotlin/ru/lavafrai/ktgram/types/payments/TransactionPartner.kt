package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object describes the source of a transaction, or its recipient for outgoing transactions.
 *
 * @property type Type of the transaction partner. Can be "fragment", "user", or "other".
 * @property withdrawalState Optional. State of the transaction if the transaction is outgoing and the partner is Fragment.
 * @property user Optional. Information about the user, if the partner is a user.
 */
@Serializable
data class TransactionPartner(
    @SerialName("type") val type: String,
    @SerialName("withdrawal_state") val withdrawalState: RevenueWithdrawalState? = null,
    @SerialName("user") val user: User? = null,
) : TelegramObject()