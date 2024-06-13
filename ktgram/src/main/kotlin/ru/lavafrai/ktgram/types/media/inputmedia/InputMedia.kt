package ru.lavafrai.ktgram.types.media.inputmedia

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.MessageEntity

@Serializable
data class InputMedia(
    @SerialName("type") val type: String,
    @Transient val mediaFile: InputFile? = null,
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
) : TelegramObject() {
    companion object {
        fun photo(
            mediaFile: InputFile,
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
            mediaFile = mediaFile,
            media = mediaFile.filename,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            showCaptionAboveMedia = showCaptionAboveMedia,
            hasSpoiler = hasSpoiler,
            thumbnail = thumbnail,
            width = width,
            height = height,
        )

        fun video(
            mediaFile: InputFile,
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
            mediaFile = mediaFile,
            media = mediaFile.filename,
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

        fun animation(
            mediaFile: InputFile,
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
            mediaFile = mediaFile,
            media = mediaFile.filename,
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

        fun audio(
            mediaFile: InputFile,
            caption: String? = null,
            parseMode: String? = null,
            captionEntities: List<MessageEntity>? = null,
            thumbnail: String? = null,
            duration: Int? = null,
            performer: String? = null,
            title: String? = null,
        ): InputMedia = InputMedia(
            type = "audio",
            mediaFile = mediaFile,
            media = mediaFile.filename,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            thumbnail = thumbnail,
            duration = duration,
            performer = performer,
            title = title,
        )

        fun document(
            mediaFile: InputFile,
            caption: String? = null,
            parseMode: String? = null,
            captionEntities: List<MessageEntity>? = null,
            thumbnail: String? = null,
            disableContentTypeDetection: Boolean? = null,
        ): InputMedia = InputMedia(
            type = "document",
            mediaFile = mediaFile,
            media = mediaFile.filename,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            thumbnail = thumbnail,
            disableContentTypeDetection = disableContentTypeDetection,
        )
    }
}

enum class InputMediaType(val value: String) {
    PHOTO("photo"),
    VIDEO("video"),
    ANIMATION("animation"),
    AUDIO("audio"),
    DOCUMENT("document"),
}

fun InputFile.toMedia(type: InputMediaType, caption: String? = null, parseMode: String? = null,): InputMedia {
    return InputMedia(
        type = type.value,
        mediaFile = this,
        media = this.filename,
        caption = caption,
        parseMode = parseMode,
    )
}