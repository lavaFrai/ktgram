package ru.lavafrai.ktgram.types.inline

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResultsButton
import ru.lavafrai.ktgram.types.media.Location


/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some default or trending results.
 *
 * @param id Unique identifier for this query
 * @param from Sender
 * @param query Text of the query (up to 256 characters)
 * @param offset Offset of the results to be returned, can be controlled by the bot
 * @param chatType Type of the chat, from which the inline query was sent. Can be either "sender" for a private chat with the inline query sender, "private", "group", "supergroup", or "channel". The chat type should be always known for requests sent from official clients and most third-party clients, unless the request was sent from a secret chat
 * @param location Sender location, only for bots that request user location
 * */
@Serializable
class InlineQuery(
    @SerialName("id") val id: String,
    @SerialName("from") val from: User,
    @SerialName("query") val query: String,
    @SerialName("offset") val offset: String,
    @SerialName("chat_type") val chatType: String? = null,
    @SerialName("location") val location: Location? = null,
): TelegramObject() {
    suspend fun answer(
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        button: InlineQueryResultsButton? = null,
    ) = bot.api.answerInlineQuery(id, results, cacheTime, isPersonal, nextOffset, button)
}