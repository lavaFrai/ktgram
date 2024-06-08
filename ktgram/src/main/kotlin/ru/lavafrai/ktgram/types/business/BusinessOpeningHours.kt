package ru.lavafrai.ktgram.types.business

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes the opening hours of a business.
 *
 * @param timeZoneName Unique name of the time zone for which the opening hours are defined
 * @param openingHours List of time intervals describing business opening hours
 */
@Serializable
class BusinessOpeningHours(
    @SerialName("time_zone_name") val timeZoneName: String,
    @SerialName("opening_hours") val openingHours: List<BusinessOpeningHoursInterval>,
) : TelegramObject()