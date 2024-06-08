package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a phone contact.
 *
 * @param phoneNumber ru.lavafrai.ktgram.types.media.Contact's phone number
 * @param firstName ru.lavafrai.ktgram.types.media.Contact's first name
 * @param lastName *Optional.* ru.lavafrai.ktgram.types.media.Contact's last name
 * @param userId *Optional.* ru.lavafrai.ktgram.types.media.Contact's user identifier in Telegram. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing this identifier.
 * @param vcard *Optional.* Additional data about the contact in the form of a vCard
 */
@Serializable
class Contact(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("user_id") val userId: Long? = null,
    @SerialName("vcard") val vcard: String? = null,
) : TelegramObject()