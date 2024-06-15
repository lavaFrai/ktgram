package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a contact with a phone number. By default, this contact will be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content instead of the contact.
 *
 * @param type Type of the result, must be contact
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param phoneNumber Contact's phone number
 * @param firstName Contact's first name
 * @param lastName Contact's last name
 * @param vcard Additional data about the contact in the form of a vCard, 0-2048 bytes
 * @param replyMarkup Inline keyboard attached to the message
 * @param inputMessageContent Content of the message to be sent instead of the contact
 * @param thumbnailUrl Url of the thumbnail for the result
 * @param thumbnailWidth Thumbnail width
 * @param thumbnailHeight Thumbnail height
 */
@Serializable
class InlineQueryResultContact(
    @SerialName("type") val type: String = "contact",
    @SerialName("id") val id: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("vcard") val vcard: String? = null,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("thumbnail_width") val thumbnailWidth: Int? = null,
    @SerialName("thumbnail_height") val thumbnailHeight: Int? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}