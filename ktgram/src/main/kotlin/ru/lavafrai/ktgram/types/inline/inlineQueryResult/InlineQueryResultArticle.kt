package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a link to an article or web page.
 *
 * @param type Type of the result, must be article
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param title Title of the result
 * @param inputMessageContent Content of the message to be sent
 * @param replyMarkup Inline keyboard attached to the message
 * @param url URL of the result
 * @param hideUrl Pass True if you don't want the URL to be shown in the message
 * @param description Short description of the result
 * @param thumbnailUrl Url of the thumbnail for the result
 * @param thumbnailWidth Thumbnail width
 * @param thumbnailHeight Thumbnail height
 */
@Serializable
class InlineQueryResultArticle(
    @SerialName("type") val type: String = "article",
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("hide_url") val hideUrl: Boolean? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width") val thumbnailWidth: Int? = null,
    @SerialName("thumbnail_height") val thumbnailHeight: Int? = null,
): InlineQueryResult()