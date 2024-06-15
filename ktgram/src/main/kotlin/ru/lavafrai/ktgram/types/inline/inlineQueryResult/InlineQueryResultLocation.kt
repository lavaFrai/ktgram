package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a location on a map. By default, the location will be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content instead of the location.
 *
 * @param type Type of the result, must be location
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param latitude Location latitude in degrees
 * @param longitude Location longitude in degrees
 * @param title Location title
 * @param horizontalAccuracy The radius of uncertainty for the location, measured in meters; 0-1500
 * @param livePeriod Period in seconds during which the location can be updated, should be between 60 and 86400, or 0x7FFFFFFF for live locations that can be edited indefinitely.
 * @param heading For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
 * @param proximityAlertRadius For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the location
 * @param thumbnailUrl Url of the thumbnail for the result
 * @param thumbnailWidth Thumbnail width
 * @param thumbnailHeight Thumbnail height
 */
@Serializable
class InlineQueryResultLocation(
    @SerialName("type") val type: String = "location",
    @SerialName("id") val id: String,
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("title") val title: String,
    @SerialName("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @SerialName("live_period") val livePeriod: Int? = null,
    @SerialName("heading") val heading: Int? = null,
    @SerialName("proximity_alert_radius") val proximityAlertRadius: Int? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width") val thumbnailWidth: Int? = null,
    @SerialName("thumbnail_height") val thumbnailHeight: Int? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}