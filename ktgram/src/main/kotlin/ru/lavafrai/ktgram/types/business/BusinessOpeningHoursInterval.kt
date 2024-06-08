package ru.lavafrai.ktgram.types.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes an interval of time during which a business is open.
 *
 * @param openingMinute The minute's sequence number in a week, starting on Monday, marking the start of the time interval during which the business is open; 0 - 7 * 24 * 60
 * @param closingMinute The minute's sequence number in a week, starting on Monday, marking the end of the time interval during which the business is open; 0 - 8 * 24 * 60
 */
@Serializable
class BusinessOpeningHoursInterval(
    @SerialName("opening_minute") val openingMinute: Int,
    @SerialName("closing_minute") val closingMinute: Int,
) : TelegramObject()