package ru.lavafrai.ktgram.client

import BotCommand
import ChatAdministratorRights
import ChatPermissions
import ReactionType
import ReplyParameters
import kotlinx.serialization.json.Json
import retrofit2.http.Field
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.client.service.factories.MediaGroupBodyFactory
import ru.lavafrai.ktgram.client.service.getClient
import ru.lavafrai.ktgram.client.service.productionTelegramApiService
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.media.inputmedia.InputMedia
import ru.lavafrai.ktgram.types.poll.InputPollOption

class TelegramApi(
    private val bot: Bot,
    private val json: Json = Json { ignoreUnknownKeys = true },
    private val service: TelegramApiService = productionTelegramApiService(bot.token, json),
    private val mediaGroupBodyFactory: MediaGroupBodyFactory = MediaGroupBodyFactory(json),
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
        offset, limit, timeout, allowedUpdates?.toTelegramList()
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
        businessConnectionId, chatId, messageThreadId, text, parseMode, entities?.toTelegramList(), linkPreviewOptions, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
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
        chatId, messageThreadId, fromChatId, messageIds.toTelegramList(), disableNotification, protectContent
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
        chatId, messageThreadId, fromChatId, messageId, caption, parseMode, captionEntities?.toTelegramList(), showCaptionAboveMedia, disableNotification, protectContent, replyParameters, replyMarkup
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
        chatId, messageThreadId, fromChatId, messageIds.toTelegramList(), disableNotification, protectContent, removeCaption
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
    ) = service.sendPhoto(businessConnectionId, chatId, messageThreadId, photo.getMultiPartBodyPart("photo"), caption, parseMode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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
    ) = service.sendAudio(businessConnectionId, chatId, messageThreadId, audio.getMultiPartBodyPart("audio"), caption, parseMode, captionEntities?.toTelegramList(), duration, performer, title, thumbnail?.getMultiPartBodyPart("thumb"), disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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
    ) = service.sendDocument(businessConnectionId, chatId, messageThreadId, document.getMultiPartBodyPart("document"), thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities?.toTelegramList(), disableContentTypeDetection, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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
    ) = service.sendVideo(businessConnectionId, chatId, messageThreadId, video.getMultiPartBodyPart("video"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, supportsStreaming, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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
    ) = service.sendAnimation(businessConnectionId, chatId, messageThreadId, animation.getMultiPartBodyPart("animation"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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
    ) = service.sendVoice(businessConnectionId, chatId, messageThreadId, voice.getMultiPartBodyPart("voice"), caption, parseMode, captionEntities?.toTelegramList(), duration, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

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

    suspend fun sendMediaGroup(
        chatId: Int,
        media: List<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
    ) = service.sendMediaGroup(businessConnectionId, chatId, messageThreadId, mediaGroupBodyFactory.createMediaGroupBody(media), disableNotification, protectContent, messageEffectId, replyParameters).getResult(bot)

    suspend fun sendLocation(
        chatId: Int,
        latitude: Float,
        longitude: Float,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        horizontalAccuracy: Float? = null,
        livePeriod: Int? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendLocation(
        businessConnectionId, chatId, messageThreadId, latitude, longitude, horizontalAccuracy, livePeriod, heading, proximityAlertRadius, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun sendVenue(
        chatId: Int,
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        foursquareId: String? = null,
        foursquareType: String? = null,
        googlePlaceId: String? = null,
        googlePlaceType: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVenue(businessConnectionId, chatId, messageThreadId, latitude, longitude, title, address, foursquareId, foursquareType, googlePlaceId, googlePlaceType, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendContact(
        chatId: Int,
        phoneNumber: String,
        firstName: String,
        lastName: String? = null,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendContact(
        businessConnectionId, chatId, messageThreadId, phoneNumber, firstName, lastName, vcard, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun sendPoll(
        chatId: Int,
        question: String,
        options: List<InputPollOption>,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        questionParseMode: String? = null,
        questionEntities: List<MessageEntity>? = null,
        isAnonymous: Boolean? = null,
        type: String? = null,
        allowsMultipleAnswers: Boolean? = null,
        correctOptionId: Int? = null,
        explanation: String? = null,
        explanationParseMode: String? = null,
        explanationEntities: List<MessageEntity>? = null,
        openPeriod: Int? = null,
        closeDate: Int? = null,
        isClosed: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendPoll(
        businessConnectionId, chatId, messageThreadId, question, questionParseMode, questionEntities?.toTelegramList(), options.toTelegramList(), isAnonymous, type, allowsMultipleAnswers, correctOptionId, explanation, explanationParseMode, explanationEntities?.toTelegramList(), openPeriod, closeDate, isClosed, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun sendDice(
        chatId: Int,
        emoji: String? = null,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendDice(
        businessConnectionId, chatId, messageThreadId, emoji, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun sendChatAction(
        chatId: Int,
        action: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
    ) = service.sendChatAction(businessConnectionId, chatId, messageThreadId, action).getResult()

    suspend fun setMessageReaction(
        chatId: Int,
        messageId: Int,
        reaction: List<ReactionType>? = null,
        isBig: Boolean? = null,
    ) = service.setMessageReaction(chatId, messageId, reaction?.toTelegramList(), isBig).getResult()

    suspend fun getUserProfilePhotos(
        userId: Int,
        offset: Int? = null,
        limit: Int? = null,
    ) = service.getUserProfilePhotos(userId, offset, limit).getResult(bot)

    suspend fun banChatMember(
        chatId: Int,
        userId: Int,
        untilDate: Int? = null,
        revokeMessages: Boolean? = null,
    ) = service.banChatMember(chatId, userId, untilDate, revokeMessages).getResult()

    suspend fun unbanChatMember(
        chatId: Int,
        userId: Int,
        onlyIfBanned: Boolean? = null,
    ) = service.unbanChatMember(chatId, userId, onlyIfBanned).getResult()

    suspend fun restrictChatMember(
        chatId: Int,
        userId: Int,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Int? = null,
    ) = service.restrictChatMember(chatId, userId, permissions, useIndependentChatPermissions, untilDate).getResult()

    suspend fun promoteChatMember(
        chatId: Int,
        userId: Int,
        isAnonymous: Boolean? = null,
        canManageChat: Boolean? = null,
        canDeleteMessages: Boolean? = null,
        canManageVideoChats: Boolean? = null,
        canRestrictMembers: Boolean? = null,
        canPromoteMembers: Boolean? = null,
        canChangeInfo: Boolean? = null,
        canInviteUsers: Boolean? = null,
        canPostStories: Boolean? = null,
        canEditStories: Boolean? = null,
        canDeleteStories: Boolean? = null,
        canPostMessages: Boolean? = null,
        canEditMessages: Boolean? = null,
        canPinMessages: Boolean? = null,
        canManageTopics: Boolean? = null,
    ) = service.promoteChatMember(chatId, userId, isAnonymous, canManageChat, canDeleteMessages, canManageVideoChats, canRestrictMembers, canPromoteMembers, canChangeInfo, canInviteUsers, canPostStories, canEditStories, canDeleteStories, canPostMessages, canEditMessages, canPinMessages, canManageTopics).getResult()

    suspend fun setChatAdministratorCustomTitle(
        chatId: Int,
        userId: Int,
        customTitle: String,
    ) = service.setChatAdministratorCustomTitle(chatId, userId, customTitle).getResult()

    suspend fun banChatSenderChat(
        chatId: Int,
        senderChatId: Int,
    ) = service.banChatSenderChat(chatId, senderChatId).getResult()

    suspend fun unbanChatSenderChat(
        chatId: Int,
        senderChatId: Int,
    ) = service.unbanChatSenderChat(chatId, senderChatId).getResult()

    suspend fun setChatPermissions(
        chatId: Int,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ) = service.setChatPermissions(chatId, permissions, useIndependentChatPermissions).getResult()

    suspend fun exportChatInviteLink(
        chatId: Int
    ) = service.exportChatInviteLink(chatId).getResult()

    suspend fun createChatInviteLink(
        chatId: Int,
        name: String? = null,
        expireDate: Int? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ) = service.createChatInviteLink(chatId, name, expireDate, memberLimit, createsJoinRequest).getResult(bot)

    suspend fun editChatInviteLink(
        chatId: Int,
        inviteLink: String,
        name: String? = null,
        expireDate: Int? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ) = service.editChatInviteLink(chatId, inviteLink, name, expireDate, memberLimit, createsJoinRequest).getResult(bot)

    suspend fun revokeChatInviteLink(
        chatId: Int,
        inviteLink: String,
    ) = service.revokeChatInviteLink(chatId, inviteLink).getResult(bot)

    suspend fun approveChatJoinRequest(
        chatId: Int,
        userId: Int,
    ) = service.approveChatJoinRequest(chatId, userId).getResult()

    suspend fun declineChatJoinRequest(
        chatId: Int,
        userId: Int,
    ) = service.declineChatJoinRequest(chatId, userId).getResult()

    suspend fun setChatPhoto(
        chatId: Int,
        photo: InputFile,
    ) = service.setChatPhoto(chatId, photo.getMultiPartBodyPart("photo")).getResult()

    suspend fun deleteChatPhoto(
        chatId: Int,
    ) = service.deleteChatPhoto(chatId).getResult()

    suspend fun setChatTitle(
        chatId: Int,
        title: String,
    ) = service.setChatTitle(chatId, title).getResult()

    suspend fun setChatDescription(
        chatId: Int,
        description: String? = null,
    ) = service.setChatDescription(chatId, description).getResult()

    suspend fun pinChatMessage(
        chatId: Int,
        messageId: Int,
        disableNotification: Boolean? = null,
    ) = service.pinChatMessage(chatId, messageId, disableNotification).getResult()

    suspend fun unpinChatMessage(
        chatId: Int,
        messageId: Int? = null,
    ) = service.unpinChatMessage(chatId, messageId).getResult()

    suspend fun unpinAllChatMessages(
        chatId: Int,
    ) = service.unpinAllChatMessages(chatId).getResult()

    suspend fun leaveChat(
        chatId: Int,
    ) = service.leaveChat(chatId).getResult()

    suspend fun getChat(
        chatId: Int,
    ) = service.getChat(chatId).getResult(bot)

    suspend fun getChatAdministrators(
        chatId: Int,
    ) = service.getChatAdministrators(chatId).getResult(bot)

    suspend fun getChatMemberCount(
        chatId: Int,
    ) = service.getChatMemberCount(chatId).getResult()

    suspend fun getChatMember(
        chatId: Int,
        userId: Int,
    ) = service.getChatMember(chatId, userId).getResult(bot)

    suspend fun setChatStickerSet(
        chatId: Int,
        stickerSetName: String,
    ) = service.setChatStickerSet(chatId, stickerSetName).getResult()

    suspend fun deleteChatStickerSet(
        chatId: Int,
    ) = service.deleteChatStickerSet(chatId).getResult()

    suspend fun getForumTopicIconStickers() = service.getForumTopicIconStickers().getResult(bot)

    suspend fun createForumTopic(
        chatId: Int,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ) = service.createForumTopic(chatId, name, iconColor, iconCustomEmojiId).getResult(bot)

    suspend fun editForumTopic(
        chatId: Int,
        messageThreadId: Int,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ) = service.editForumTopic(chatId, messageThreadId, name, iconCustomEmojiId).getResult()

    suspend fun closeForumTopic(
        chatId: Int,
        messageThreadId: Int,
    ) = service.closeForumTopic(chatId, messageThreadId).getResult()

    suspend fun reopenForumTopic(
        chatId: Int,
        messageThreadId: Int,
    ) = service.reopenForumTopic(chatId, messageThreadId).getResult()

    suspend fun deleteForumTopic(
        chatId: Int,
        messageThreadId: Int,
    ) = service.deleteForumTopic(chatId, messageThreadId).getResult()

    suspend fun unpinAllForumTopicMessages(
        chatId: Int,
        messageThreadId: Int,
    ) = service.unpinAllForumTopicMessages(chatId, messageThreadId).getResult()

    suspend fun editGeneralForumTopic(
        chatId: Int,
        name: String,
    ) = service.editGeneralForumTopic(chatId, name).getResult()

    suspend fun closeGeneralForumTopic(
        chatId: Int,
    ) = service.closeGeneralForumTopic(chatId).getResult()

    suspend fun reopenGeneralForumTopic(
        chatId: Int,
    ) = service.reopenGeneralForumTopic(chatId).getResult()

    suspend fun hideGeneralForumTopic(
        chatId: Int,
    ) = service.hideGeneralForumTopic(chatId).getResult()

    suspend fun unhideGeneralForumTopic(
        chatId: Int,
    ) = service.unhideGeneralForumTopic(chatId).getResult()

    suspend fun unpinAllGeneralForumTopicMessages(
        chatId: Int,
    ) = service.unpinAllGeneralForumTopicMessages(chatId).getResult()

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Int? = null,
    ) = service.answerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime).getResult()

    suspend fun getUserChatBoosts(
        chatId: Int,
        userId: Int,
    ) = service.getUserChatBoosts(chatId, userId).getResult(bot)

    suspend fun getBusinessConnection(
        businessConnectionId: String,
    ) = service.getBusinessConnection(businessConnectionId).getResult(bot)

    suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ) = service.setMyCommands(commands.toTelegramList(), scope, languageCode).getResult()

    suspend fun deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ) = service.deleteMyCommands(scope, languageCode).getResult()

    suspend fun getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ) = service.getMyCommands(scope, languageCode).getResult(bot)

    suspend fun setMyName(
        name: String? = null,
        languageCode: String? = null,
    ) = service.setMyName(name, languageCode).getResult()

    suspend fun getMyName(
        languageCode: String? = null,
    ) = service.getMyName(languageCode).getResult(bot)

    suspend fun setMyDescription(
        description: String? = null,
        languageCode: String? = null,
    ) = service.setMyDescription(description, languageCode).getResult()

    suspend fun getMyDescription(
        languageCode: String? = null,
    ) = service.getMyDescription(languageCode).getResult(bot)

    suspend fun setMyShortDescription(
        shortDescription: String? = null,
        languageCode: String? = null,
    ) = service.setMyShortDescription(shortDescription, languageCode).getResult()

    suspend fun getMyShortDescription(
        languageCode: String? = null,
    ) = service.getMyShortDescription(languageCode).getResult(bot)

    suspend fun setChatMenuButton(
        chatId: Int? = null,
        menuButton: MenuButton? = null,
    ) = service.setChatMenuButton(chatId, menuButton).getResult()

    suspend fun getChatMenuButton(
        chatId: Int? = null,
    ) = service.getChatMenuButton(chatId).getResult(bot)

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ) = service.setMyDefaultAdministratorRights(rights, forChannels).getResult()

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ) = service.getMyDefaultAdministratorRights(forChannels).getResult(bot)
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