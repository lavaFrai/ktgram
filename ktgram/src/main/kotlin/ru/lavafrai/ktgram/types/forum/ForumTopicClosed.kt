package ru.lavafrai.ktgram.types.forum

import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about a forum topic closed in the chat. Currently, holds no information.
 */
@Serializable
class ForumTopicClosed: TelegramObject() {
}