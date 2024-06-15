package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to a page containing an embedded video player or a video file. By default, this video file will be sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the video.
 *
 * If an InlineQueryResultVideo message contains an embedded video (e.g., YouTube), you must replace its content using input_message_content.
 *
 * @param type Type of the result, must be video
 * @param id Unique identifier for this result, 1-64 bytes
 * @param videoUrl A valid URL for the embedded video player or video file
 * @param mimeType MIME type of the content of the video URL, “text/html” or “video/mp4”
 * @param thumbnailUrl URL of the thumbnail (JPEG only) for the video
 * @param title Title for the result
 * @param caption Caption of the video to be sent, 0-1024 characters after entities parsing
 * @param parseMode Mode for parsing entities in the video caption. See formatting options for more details.
 * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
 * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
 * @param videoWidth Video width
 * @param videoHeight Video height
 * @param videoDuration Video duration in seconds
 * @param description Short description of the result
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the video. This field is required if InlineQueryResultVideo is used to send an HTML-page as a result (e.g., a YouTube video).
 */
@Serializable
class InlineQueryResultVideo(
    @SerialName("type") val type: String = "video",
    @SerialName("id") val id: String,
    @SerialName("video_url") val videoUrl: String,
    @SerialName("mime_type") val mimeType: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("title") val title: String,
    @SerialName("caption") val caption: String? = null,
    @SerialName("parse_mode") val parseMode: String? = null,
    @SerialName("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @SerialName("show_caption_above_media") val showCaptionAboveMedia: Boolean? = null,
    @SerialName("video_width") val videoWidth: Int? = null,
    @SerialName("video_height") val videoHeight: Int? = null,
    @SerialName("video_duration") val videoDuration: Int? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}