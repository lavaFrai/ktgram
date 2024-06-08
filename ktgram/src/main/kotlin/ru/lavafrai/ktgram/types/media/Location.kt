package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a point on the map.
 *
 * @param latitude Latitude as defined by sender
 * @param longitude Longitude as defined by sender
 * @param horizontalAccuracy *Optional.* The radius of uncertainty for the location, measured in meters; 0-1500
 * @param livePeriod *Optional.* Time relative to the message sending date, during which the location can be updated; in seconds. For active live locations only.
 * @param heading *Optional.* The direction in which user is moving, in degrees; 1-360. For active live locations only.
 * @param proximityAlertRadius *Optional.* The maximum distance for proximity alerts about approaching another chat member, in meters. For sent live locations only.
 */
@Serializable
class Location(
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @SerialName("live_period") val livePeriod: Int? = null,
    @SerialName("heading") val heading: Int? = null,
    @SerialName("proximity_alert_radius") val proximityAlertRadius: Int? = null,
) : TelegramObject()