package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents an animation file (GIF or H.264/MPEG-4 AVC video without sound).
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
 * @param width ru.lavafrai.ktgram.types.media.Video width as defined by sender
 * @param height ru.lavafrai.ktgram.types.media.Video height as defined by sender
 * @param duration Duration of the video in seconds as defined by sender
 * @param thumbnail *Optional.* ru.lavafrai.ktgram.types.media.Animation thumbnail as defined by sender
 * @param fileName *Optional.* Original animation filename as defined by sender
 * @param mimeType *Optional.* MIME type of the file as defined by sender
 * @param fileSize *Optional.* ru.lavafrai.ktgram.types.media.File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
 */
@Serializable
class Animation(
    @SerialName("file_id") val fileId: String,
    @SerialName("file_unique_id") val fileUniqueId: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("thumbnail") val thumbnail: PhotoSize? = null,
    @SerialName("file_name") val fileName: String? = null,
    @SerialName("mime_type") val mimeType: String? = null,
    @SerialName("file_size") val fileSize: Int? = null,
) : TelegramObject()