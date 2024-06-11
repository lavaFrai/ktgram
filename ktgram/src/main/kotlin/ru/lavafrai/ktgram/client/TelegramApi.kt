package ru.lavafrai.ktgram.client

import ReplyParameters
import retrofit2.http.Field
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.client.service.getClient
import ru.lavafrai.ktgram.types.ReplyMarkup
import ru.lavafrai.ktgram.types.User
import ru.lavafrai.ktgram.types.getResult
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity

class TelegramApi(
    private val bot: Bot,
    private val service: TelegramApiService
) {
    val client: OkHttpClient
        get() = service.getClient()

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

    suspend fun getFile(fileId: String) = service.getFile(fileId).getResult(bot)

    suspend fun forwardMessage(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageId: Int,
    ) = service.forwardMessage(
        chatId, messageThreadId, fromChatId, disableNotification, protectContent, messageId
    ).getResult(bot)

    suspend fun forwardMessages(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        messageIds: List<Int>,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ) = service.forwardMessages(
        chatId, messageThreadId, fromChatId, messageIds, disableNotification, protectContent
    ).getResult()

    suspend fun copyMessage(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        messageId: Int,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.copyMessage(
        chatId, messageThreadId, fromChatId, messageId, caption, parseMode, captionEntities, showCaptionAboveMedia, disableNotification, protectContent, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun copyMessages(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        messageIds: List<Int>,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ) = service.copyMessages(
        chatId, messageThreadId, fromChatId, messageIds, disableNotification, protectContent, removeCaption
    ).getResult()

    suspend fun sendPhoto(
        chatId: Int,
        photo: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendPhoto(businessConnectionId, chatId, messageThreadId, photo.getMultiPartBodyPart("photo"), caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    @Deprecated("Use sendPhoto with InputFile instead")
    suspend fun sendPhoto(
        chatId: Int,
        photo: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendPhoto(businessConnectionId, chatId, messageThreadId, photo, caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendAudio(
        chatId: Int,
        audio: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: InputFile? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendAudio(businessConnectionId, chatId, messageThreadId, audio.getMultiPartBodyPart("audio"), caption, parseMode, captionEntities, duration, performer, title, thumbnail?.getMultiPartBodyPart("thumb"), disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendDocument(
        chatId: Int,
        document: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = false,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendDocument(businessConnectionId, chatId, messageThreadId, document.getMultiPartBodyPart("document"), thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities, disableContentTypeDetection, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVideo(
        chatId: Int,
        video: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVideo(businessConnectionId, chatId, messageThreadId, video.getMultiPartBodyPart("video"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, supportsStreaming, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendAnimation(
        chatId: Int,
        animation: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendAnimation(businessConnectionId, chatId, messageThreadId, animation.getMultiPartBodyPart("animation"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVoice(
        chatId: Int,
        voice: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVoice(businessConnectionId, chatId, messageThreadId, voice.getMultiPartBodyPart("voice"), caption, parseMode, captionEntities, duration, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVideoNote(
        chatId: Int,
        videoNote: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: InputFile? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVideoNote(businessConnectionId, chatId, messageThreadId, videoNote.getMultiPartBodyPart("video_note"), duration, length, thumbnail?.getMultiPartBodyPart("thumb"), disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)
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