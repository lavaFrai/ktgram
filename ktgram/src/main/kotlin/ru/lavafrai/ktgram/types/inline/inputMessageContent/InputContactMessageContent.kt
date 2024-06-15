package ru.lavafrai.ktgram.types.inline.inputMessageContent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the content of a contact message to be sent as the result of an inline query.
 *
 * @param phoneNumber Contact's phone number
 * @param firstName Contact's first name
 * @param lastName Contact's last name
 * @param vcard Additional data about the contact in the form of a vCard, 0-2048 bytes
 */
@Serializable
class InputContactMessageContent(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("vcard") val vcard: String? = null,
): InputMessageContent() {
}