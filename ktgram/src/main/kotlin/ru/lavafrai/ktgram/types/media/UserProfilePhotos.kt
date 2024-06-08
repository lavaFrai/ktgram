package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represent a user's profile pictures.
 *
 * @param totalCount Total number of profile pictures the target user has
 * @param photos Requested profile pictures (in up to 4 sizes each)
 */
@Serializable
class UserProfilePhotos(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("photos") val photos: List<List<PhotoSize>>,
) : TelegramObject()