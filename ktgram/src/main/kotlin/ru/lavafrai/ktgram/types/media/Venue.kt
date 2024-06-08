package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a venue.
 *
 * @param location ru.lavafrai.ktgram.types.media.Venue location. Can't be a live location
 * @param title Name of the venue
 * @param address Address of the venue
 * @param foursquareId *Optional.* Foursquare identifier of the venue
 * @param foursquareType *Optional.* Foursquare type of the venue. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
 * @param googlePlaceId *Optional.* Google Places identifier of the venue
 * @param googlePlaceType *Optional.* Google Places type of the venue. (See supported types.)
 */
@Serializable
class Venue(
    @SerialName("location") val location: Location,
    @SerialName("title") val title: String,
    @SerialName("address") val address: String,
    @SerialName("foursquare_id") val foursquareId: String? = null,
    @SerialName("foursquare_type") val foursquareType: String? = null,
    @SerialName("google_place_id") val googlePlaceId: String? = null,
    @SerialName("google_place_type") val googlePlaceType: String? = null,
) : TelegramObject()