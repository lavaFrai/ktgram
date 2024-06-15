package ru.lavafrai.ktgram.types.inline.inputMessageContent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the content of a venue message to be sent as the result of an inline query.
 *
 * @param latitude Latitude of the venue in degrees
 * @param longitude Longitude of the venue in degrees
 * @param title Name of the venue
 * @param address Address of the venue
 * @param foursquareId Foursquare identifier of the venue, if known
 * @param foursquareType Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
 * @param googlePlaceId Google Places identifier of the venue
 * @param googlePlaceType Google Places type of the venue. (See supported types.)
 */
@Serializable
class InputVenueMessageContent(
    @SerialName("latitude") val latitude: Float,
    @SerialName("longitude") val longitude: Float,
    @SerialName("title") val title: String,
    @SerialName("address") val address: String,
    @SerialName("foursquare_id") val foursquareId: String? = null,
    @SerialName("foursquare_type") val foursquareType: String? = null,
    @SerialName("google_place_id") val googlePlaceId: String? = null,
    @SerialName("google_place_type") val googlePlaceType: String? = null,
): InputMessageContent() {
}