package ru.lavafrai.ktgram.client

import ReplyParameters
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.types.ReplyMarkup
import ru.lavafrai.ktgram.types.User
import ru.lavafrai.ktgram.types.getResult
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity

class TelegramApi(
    private val bot: Bot,
    private val service: TelegramApiService
) {
    suspend fun getMe(): User = service.getMe().getResult(bot)

    suspend fun getUpdates(
        offset: Int? = 0,
        limit: Int? = 1,
        timeout: Int? = 0,
        allowedUpdates: List<String>? = getAllUpdates(),
    ) = service.getUpdates(
        offset, limit, timeout, allowedUpdates
    ).getResult(bot)

    suspend fun sendMessage(
        chatId: Int,
        text: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        parseMode: String? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendMessage(
        businessConnectionId, chatId, messageThreadId, text, parseMode, entities, linkPreviewOptions, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    ).getResult(bot)
}

fun getAllUpdates() = listOf(
    "message",
    "edited_message",
    "channel_post",
    "edited_channel_post",
    "business_connection",
    "business_message",
    "edited_business_message",
    "deleted_business_messages",
    "message_reaction",
    "message_reaction_count",
    "inline_query",
    "chosen_inline_result",
    "callback_query",
    "shipping_query",
    "pre_checkout_query",
    "poll",
    "poll_answer",
    "my_chat_member",
    "chat_member",
    "chat_join_request",
    "chat_boost",
    "removed_chat_boost",
)