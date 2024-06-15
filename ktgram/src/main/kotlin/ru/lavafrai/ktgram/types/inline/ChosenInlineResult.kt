package ru.lavafrai.ktgram.types.inline

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User
import ru.lavafrai.ktgram.types.media.Location

/**
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
 *
 * @param resultId The unique identifier for the result that was chosen
 * @param from The user that chose the result
 * @param location Sender location, only for bots that require user location
 * @param inlineMessageId Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message. Will be also received in callback queries and can be used to edit the message.
 * @param query The query that was used to obtain the result
 *
 * Note: It is necessary to enable inline feedback via @BotFather in order to receive these objects in updates.
 */
@Serializable
class ChosenInlineResult(
    @SerialName("result_id") val resultId: String,
    @SerialName("from") val from: User,
    @SerialName("location") val location: Location? = null,
    @SerialName("inline_message_id") val inlineMessageId: String? = null,
    @SerialName("query") val query: String,
): TelegramObject() {
}