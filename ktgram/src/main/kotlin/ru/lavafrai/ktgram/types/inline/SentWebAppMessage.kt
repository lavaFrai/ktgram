package ru.lavafrai.ktgram.types.inline

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes an inline message sent by a Web App on behalf of a user.
 *
 * @param inlineMessageId Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message.
 */
@Serializable
class SentWebAppMessage(
    @SerialName("inline_message_id") val inlineMessageId: String? = null,
): TelegramObject() {
}