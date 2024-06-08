package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a story.
 *
 * @param chat Chat that posted the story
 * @param id Unique identifier for the story in the chat
 */
@Serializable
class Story(
    @SerialName("chat") val chat: Chat,
    @SerialName("id") val id: Int,
) : TelegramObject()