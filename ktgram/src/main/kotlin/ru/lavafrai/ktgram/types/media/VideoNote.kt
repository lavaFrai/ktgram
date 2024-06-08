package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject


/**
 * This object represents a video message (available in Telegram apps as of v.4.0).
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
 * @param length ru.lavafrai.ktgram.types.media.Video width and height (diameter of the video message) as defined by sender
 * @param duration Duration of the video in seconds as defined by sender
 * @param thumbnail *Optional.* ru.lavafrai.ktgram.types.media.Video thumbnail
 * @param fileSize *Optional.* ru.lavafrai.ktgram.types.media.File size in bytes
 */
@Serializable
class VideoNote(
    @SerialName("file_id") val fileId: String,
    @SerialName("file_unique_id") val fileUniqueId: String,
    @SerialName("length") val length: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("thumbnail") val thumbnail: PhotoSize? = null,
    @SerialName("file_size") val fileSize: Int? = null,
) : TelegramObject()