package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes a Telegram Star transaction.
 *
 * @param id Unique identifier of the transaction. Coincides with the identifier of the original
 * transaction for refund transactions. Coincides with SuccessfulPayment.telegram_payment_charge_id
 * for successful incoming payments from users.
 * @param amount Number of Telegram Stars transferred by the transaction
 * @param date Date the transaction was created in Unix time
 * @param source Optional. Source of an incoming transaction (e.g., a user purchasing goods or
 * services, Fragment refunding a failed withdrawal). Only for incoming transactions
 * @param receiver Optional. Receiver of an outgoing transaction (e.g., a user for a purchase
 * refund, Fragment for a withdrawal). Only for outgoing transactions
 * @param incoming True, if the transaction is incoming
 * @param outgoing True, if the transaction is outgoing
 * @param refund True, if the transaction is outgoing
 */
@Serializable
data class StarTransaction(
    @SerialName("id") val id: String,
    @SerialName("amount") val amount: Int,
    @SerialName("date") val date: Int,
    @SerialName("source") val source: TransactionPartner? = null,
    @SerialName("receiver") val receiver: TransactionPartner? = null,
) : TelegramObject() {
    val incoming: Boolean
        get() = source != null

    val outgoing: Boolean
        get() = receiver != null

    val refund: Boolean
        get() = outgoing

    val delta: Int
        get() = if (incoming) amount else -amount
}