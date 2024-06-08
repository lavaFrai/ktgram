package ru.lavafrai.ktgram.types.media.inputmedia

import kotlinx.serialization.SerialName
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.media.MessageEntity

class InputMedia(
    @SerialName("type") val type: String,
    @SerialName("media") val media: String,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media") val showCaptionAboveMedia: Boolean? = null,
    @SerialName("has_spoiler") val hasSpoiler: Boolean? = null,
    @SerialName("performer") val performer: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("duration") val duration: Int? = null,
    @SerialName("supports_streaming") val supportsStreaming: Boolean? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("disable_content_type_detection") val disableContentTypeDetection: Boolean? = null,
) : TelegramObject()


/**
 * Represents a photo to be sent.
 */
fun inputMediaPhoto(
    media: String,
    caption: String? = null,
    parseMode: String? = null,
    captionEntities: List<MessageEntity>? = null,
    showCaptionAboveMedia: Boolean? = null,
    hasSpoiler: Boolean? = null,
    thumbnail: String? = null,
    width: Int? = null,
    height: Int? = null,
): InputMedia = InputMedia(
    type = "photo",
    media = media,
    caption = caption,
    parseMode = parseMode,
    captionEntities = captionEntities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    hasSpoiler = hasSpoiler,
    thumbnail = thumbnail,
    width = width,
    height = height,
)


/**
 * Represents a video to be sent.
 */
fun inputMediaVideo(
    media: String,
    caption: String? = null,
    parseMode: String? = null,
    captionEntities: List<MessageEntity>? = null,
    showCaptionAboveMedia: Boolean? = null,
    hasSpoiler: Boolean? = null,
    thumbnail: String? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
    supportsStreaming: Boolean? = null,
    disableContentTypeDetection: Boolean? = null,
): InputMedia = InputMedia(
    type = "video",
    media = media,
    caption = caption,
    parseMode = parseMode,
    captionEntities = captionEntities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    hasSpoiler = hasSpoiler,
    thumbnail = thumbnail,
    width = width,
    height = height,
    duration = duration,
    supportsStreaming = supportsStreaming,
    disableContentTypeDetection = disableContentTypeDetection,
)


/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent.
 */
fun inputMediaAnimation(
    media: String,
    caption: String? = null,
    parseMode: String? = null,
    captionEntities: List<MessageEntity>? = null,
    showCaptionAboveMedia: Boolean? = null,
    hasSpoiler: Boolean? = null,
    thumbnail: String? = null,
    width: Int? = null,
    height: Int? = null,
    duration: Int? = null,
): InputMedia = InputMedia(
    type = "animation",
    media = media,
    caption = caption,
    parseMode = parseMode,
    captionEntities = captionEntities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    hasSpoiler = hasSpoiler,
    thumbnail = thumbnail,
    width = width,
    height = height,
    duration = duration,
)


/**
 * Represents an audio file to be treated as music to be sent.
 */
fun inputMediaAudio(
    media: String,
    caption: String? = null,
    parseMode: String? = null,
    captionEntities: List<MessageEntity>? = null,
    thumbnail: String? = null,
    duration: Int? = null,
    performer: String? = null,
    title: String? = null,
): InputMedia = InputMedia(
    type = "audio",
    media = media,
    caption = caption,
    parseMode = parseMode,
    captionEntities = captionEntities,
    thumbnail = thumbnail,
    duration = duration,
    performer = performer,
    title = title,
)


/**
 * Represents a document to be sent.
 */
fun inputMediaDocument(
    media: String,
    caption: String? = null,
    parseMode: String? = null,
    captionEntities: List<MessageEntity>? = null,
    thumbnail: String? = null,
    disableContentTypeDetection: Boolean? = null,
): InputMedia = InputMedia(
    type = "document",
    media = media,
    caption = caption,
    parseMode = parseMode,
    captionEntities = captionEntities,
    thumbnail = thumbnail,
    disableContentTypeDetection = disableContentTypeDetection,
)