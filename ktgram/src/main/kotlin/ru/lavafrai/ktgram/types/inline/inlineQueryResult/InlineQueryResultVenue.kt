package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a venue. By default, the venue will be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content instead of the venue.
 *
 * @param type Type of the result, must be venue
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param latitude Latitude of the venue location in degrees
 * @param longitude Longitude of the venue location in degrees
 * @param title Title of the venue
 * @param address Address of the venue
 * @param foursquareId Foursquare identifier of the venue if known
 * @param foursquareType Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
 * @param googlePlaceId Google Places identifier of the venue
 * @param googlePlaceType Google Places type of the venue. (See supported types.)
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the venue
 * @param thumbnailUrl Url of the thumbnail for the result
 * @param thumbnailWidth Thumbnail width
 * @param thumbnailHeight Thumbnail height
 */
@Serializable
class InlineQueryResultVenue(
    @SerialName("type") val type: String = "venue",
    @SerialName("id") val id: String,
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("title") val title: String,
    @SerialName("address") val address: String,
    @SerialName("foursquare_id") val foursquareId: String? = null,
    @SerialName("foursquare_type") val foursquareType: String? = null,
    @SerialName("google_place_id") val googlePlaceId: String? = null,
    @SerialName("google_place_type") val googlePlaceType: String? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width") val thumbnailWidth: Int? = null,
    @SerialName("thumbnail_height") val thumbnailHeight: Int? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}