package ru.lavafrai.ktgram.types.payments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object describes the state of a revenue withdrawal operation.
 *
 * @property type Type of the state. Can be "pending", "succeeded", or "failed".
 * @property date Optional. Date the withdrawal was completed in Unix time, if the state is "succeeded".
 * @property url Optional. An HTTPS URL that can be used to see transaction details, if the state is "succeeded".
 */
@Serializable
data class RevenueWithdrawalState(
    @SerialName("type") val type: String,
    @SerialName("date") val date: Int? = null,
    @SerialName("url") val url: String? = null,
) : TelegramObject()