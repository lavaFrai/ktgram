package ru.lavafrai.ktgram.types.poll

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents an answer of a user in a non-anonymous poll.
 *
 * @param pollId Unique poll identifier
 * @param voterChat *Optional.* The chat that changed the answer to the poll, if the voter is anonymous
 * @param user *Optional.* The user that changed the answer to the poll, if the voter isn't anonymous
 * @param optionIds 0-based identifiers of chosen answer options. May be empty if the vote was retracted.
 */
@Serializable
class PollAnswer(
    @SerialName("poll_id") val pollId: String,
    @SerialName("voter_chat") val voterChat: Chat? = null,
    @SerialName("user") val user: User? = null,
    @SerialName("option_ids") val optionIds: List<Int>,
) : TelegramObject()