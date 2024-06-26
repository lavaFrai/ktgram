package ru.lavafrai.ktgram.client

import BotCommand
import ChatAdministratorRights
import ChatPermissions
import ReactionType
import ReplyParameters
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.http.Field
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.client.service.factories.MediaGroupBodyFactory
import ru.lavafrai.ktgram.client.service.productionOkHttpClient
import ru.lavafrai.ktgram.client.service.productionTelegramApiService
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResultsButton
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.media.inputmedia.InputMedia
import ru.lavafrai.ktgram.types.payments.LabeledPrice
import ru.lavafrai.ktgram.types.payments.ShippingOption
import ru.lavafrai.ktgram.types.poll.InputPollOption
import ru.lavafrai.ktgram.types.replymarkup.ReplyMarkup
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup
import java.net.Proxy

class TelegramApi(
    private val bot: Bot,
    val proxy: Proxy? = null,
    val json: Json = TelegramObject.tolerantJson,
    private val networkClient: OkHttpClient = productionOkHttpClient(proxy),
    private val service: TelegramApiService = productionTelegramApiService(bot.token, json, networkClient),
    private val mediaGroupBodyFactory: MediaGroupBodyFactory = MediaGroupBodyFactory(json),
) {
    val client: OkHttpClient
        get() = networkClient

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
        chatId: Long,
        text: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendMessage(
        businessConnectionId, chatId, messageThreadId, text, parseMode?.mode, entities?.toTelegramList(), linkPreviewOptions, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    ).getResult(bot)

    suspend fun getFile(fileId: String) = service.getFile(fileId).getResult(bot)

    suspend fun forwardMessage(
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageId: Int,
    ) = service.forwardMessage(
        chatId, messageThreadId, fromChatId, disableNotification, protectContent, messageId
    ).getResult(bot)

    suspend fun forwardMessages(
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
        messageIds: List<Int>,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ) = service.forwardMessages(
        chatId, messageThreadId, fromChatId, messageIds.toTelegramList(), disableNotification, protectContent
    ).getResult()

    suspend fun copyMessage(
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
        messageId: Int,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.copyMessage(
        chatId, messageThreadId, fromChatId, messageId, caption, parseMode?.mode, captionEntities?.toTelegramList(), showCaptionAboveMedia, disableNotification, protectContent, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun copyMessages(
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
        messageIds: List<Int>,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ) = service.copyMessages(
        chatId, messageThreadId, fromChatId, messageIds.toTelegramList(), disableNotification, protectContent, removeCaption
    ).getResult()

    suspend fun sendPhoto(
        chatId: Long,
        photo: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendPhoto(businessConnectionId, chatId, messageThreadId, photo.getMultiPartBodyPart("photo"), caption, parseMode?.mode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendAudio(
        chatId: Long,
        audio: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
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
    ) = service.sendAudio(businessConnectionId, chatId, messageThreadId, audio.getMultiPartBodyPart("audio"), caption, parseMode?.mode, captionEntities?.toTelegramList(), duration, performer, title, thumbnail?.getMultiPartBodyPart("thumb"), disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendDocument(
        chatId: Long,
        document: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = false,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendDocument(businessConnectionId, chatId, messageThreadId, document.getMultiPartBodyPart("document"), thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode?.mode, captionEntities?.toTelegramList(), disableContentTypeDetection, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVideo(
        chatId: Long,
        video: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVideo(businessConnectionId, chatId, messageThreadId, video.getMultiPartBodyPart("video"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode?.mode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, supportsStreaming, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendAnimation(
        chatId: Long,
        animation: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendAnimation(businessConnectionId, chatId, messageThreadId, animation.getMultiPartBodyPart("animation"), duration, width, height, thumbnail?.getMultiPartBodyPart("thumb"), caption, parseMode?.mode, captionEntities?.toTelegramList(), showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVoice(
        chatId: Long,
        voice: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendVoice(businessConnectionId, chatId, messageThreadId, voice.getMultiPartBodyPart("voice"), caption, parseMode?.mode, captionEntities?.toTelegramList(), duration, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun sendVideoNote(
        chatId: Long,
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
        chatId: Long,
        media: List<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
    ) = service.sendMediaGroup(businessConnectionId, chatId, messageThreadId, mediaGroupBodyFactory.createMediaGroupBody(media), disableNotification, protectContent, messageEffectId, replyParameters).getResult(bot)

    suspend fun sendLocation(
        chatId: Long,
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
        chatId: Long,
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
        chatId: Long,
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
        chatId: Long,
        question: String,
        options: List<InputPollOption>,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        questionParseMode: ParseMode? = null,
        questionEntities: List<MessageEntity>? = null,
        isAnonymous: Boolean? = null,
        type: String? = null,
        allowsMultipleAnswers: Boolean? = null,
        correctOptionId: Int? = null,
        explanation: String? = null,
        explanationParseMode: ParseMode? = null,
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
        businessConnectionId, chatId, messageThreadId, question, questionParseMode?.mode, questionEntities?.toTelegramList(), options.toTelegramList(), isAnonymous, type, allowsMultipleAnswers, correctOptionId, explanation, explanationParseMode?.mode, explanationEntities?.toTelegramList(), openPeriod, closeDate, isClosed, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup
    ).getResult(bot)

    suspend fun sendDice(
        chatId: Long,
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
        chatId: Long,
        action: ChatAction,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
    ) = service.sendChatAction(businessConnectionId, chatId, messageThreadId, action.action).getResult()

    suspend fun setMessageReaction(
        chatId: Long,
        messageId: Int,
        reaction: List<ReactionType>? = null,
        isBig: Boolean? = null,
    ) = service.setMessageReaction(chatId, messageId, reaction?.toTelegramList(), isBig).getResult()

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Int? = null,
        limit: Int? = null,
    ) = service.getUserProfilePhotos(userId, offset, limit).getResult(bot)

    suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Int? = null,
        revokeMessages: Boolean? = null,
    ) = service.banChatMember(chatId, userId, untilDate, revokeMessages).getResult()

    suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ) = service.unbanChatMember(chatId, userId, onlyIfBanned).getResult()

    suspend fun restrictChatMember(
        chatId: Long,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Int? = null,
    ) = service.restrictChatMember(chatId, userId, permissions, useIndependentChatPermissions, untilDate).getResult()

    suspend fun promoteChatMember(
        chatId: Long,
        userId: Long,
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
        chatId: Long,
        userId: Long,
        customTitle: String,
    ) = service.setChatAdministratorCustomTitle(chatId, userId, customTitle).getResult()

    suspend fun banChatSenderChat(
        chatId: Long,
        senderChatId: Long,
    ) = service.banChatSenderChat(chatId, senderChatId).getResult()

    suspend fun unbanChatSenderChat(
        chatId: Long,
        senderChatId: Long,
    ) = service.unbanChatSenderChat(chatId, senderChatId).getResult()

    suspend fun setChatPermissions(
        chatId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ) = service.setChatPermissions(chatId, permissions, useIndependentChatPermissions).getResult()

    suspend fun exportChatInviteLink(
        chatId: Long
    ) = service.exportChatInviteLink(chatId).getResult()

    suspend fun createChatInviteLink(
        chatId: Long,
        name: String? = null,
        expireDate: Int? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ) = service.createChatInviteLink(chatId, name, expireDate, memberLimit, createsJoinRequest).getResult(bot)

    suspend fun editChatInviteLink(
        chatId: Long,
        inviteLink: String,
        name: String? = null,
        expireDate: Int? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ) = service.editChatInviteLink(chatId, inviteLink, name, expireDate, memberLimit, createsJoinRequest).getResult(bot)

    suspend fun revokeChatInviteLink(
        chatId: Long,
        inviteLink: String,
    ) = service.revokeChatInviteLink(chatId, inviteLink).getResult(bot)

    suspend fun approveChatJoinRequest(
        chatId: Long,
        userId: Long,
    ) = service.approveChatJoinRequest(chatId, userId).getResult()

    suspend fun declineChatJoinRequest(
        chatId: Long,
        userId: Long,
    ) = service.declineChatJoinRequest(chatId, userId).getResult()

    suspend fun setChatPhoto(
        chatId: Long,
        photo: InputFile,
    ) = service.setChatPhoto(chatId, photo.getMultiPartBodyPart("photo")).getResult()

    suspend fun deleteChatPhoto(
        chatId: Long,
    ) = service.deleteChatPhoto(chatId).getResult()

    suspend fun setChatTitle(
        chatId: Long,
        title: String,
    ) = service.setChatTitle(chatId, title).getResult()

    suspend fun setChatDescription(
        chatId: Long,
        description: String? = null,
    ) = service.setChatDescription(chatId, description).getResult()

    suspend fun pinChatMessage(
        chatId: Long,
        messageId: Int,
        disableNotification: Boolean? = null,
    ) = service.pinChatMessage(chatId, messageId, disableNotification).getResult()

    suspend fun unpinChatMessage(
        chatId: Long,
        messageId: Int? = null,
    ) = service.unpinChatMessage(chatId, messageId).getResult()

    suspend fun unpinAllChatMessages(
        chatId: Long,
    ) = service.unpinAllChatMessages(chatId).getResult()

    suspend fun leaveChat(
        chatId: Long,
    ) = service.leaveChat(chatId).getResult()

    suspend fun getChat(
        chatId: Long,
    ) = service.getChat(chatId).getResult(bot)

    suspend fun getChatAdministrators(
        chatId: Long,
    ) = service.getChatAdministrators(chatId).getResult(bot)

    suspend fun getChatMemberCount(
        chatId: Long,
    ) = service.getChatMemberCount(chatId).getResult()

    suspend fun getChatMember(
        chatId: Long,
        userId: Long,
    ) = service.getChatMember(chatId, userId).getResult(bot)

    suspend fun setChatStickerSet(
        chatId: Long,
        stickerSetName: String,
    ) = service.setChatStickerSet(chatId, stickerSetName).getResult()

    suspend fun deleteChatStickerSet(
        chatId: Long,
    ) = service.deleteChatStickerSet(chatId).getResult()

    suspend fun getForumTopicIconStickers() = service.getForumTopicIconStickers().getResult(bot)

    suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ) = service.createForumTopic(chatId, name, iconColor, iconCustomEmojiId).getResult(bot)

    suspend fun editForumTopic(
        chatId: Long,
        messageThreadId: Int,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ) = service.editForumTopic(chatId, messageThreadId, name, iconCustomEmojiId).getResult()

    suspend fun closeForumTopic(
        chatId: Long,
        messageThreadId: Int,
    ) = service.closeForumTopic(chatId, messageThreadId).getResult()

    suspend fun reopenForumTopic(
        chatId: Long,
        messageThreadId: Int,
    ) = service.reopenForumTopic(chatId, messageThreadId).getResult()

    suspend fun deleteForumTopic(
        chatId: Long,
        messageThreadId: Int,
    ) = service.deleteForumTopic(chatId, messageThreadId).getResult()

    suspend fun unpinAllForumTopicMessages(
        chatId: Long,
        messageThreadId: Int,
    ) = service.unpinAllForumTopicMessages(chatId, messageThreadId).getResult()

    suspend fun editGeneralForumTopic(
        chatId: Long,
        name: String,
    ) = service.editGeneralForumTopic(chatId, name).getResult()

    suspend fun closeGeneralForumTopic(
        chatId: Long,
    ) = service.closeGeneralForumTopic(chatId).getResult()

    suspend fun reopenGeneralForumTopic(
        chatId: Long,
    ) = service.reopenGeneralForumTopic(chatId).getResult()

    suspend fun hideGeneralForumTopic(
        chatId: Long,
    ) = service.hideGeneralForumTopic(chatId).getResult()

    suspend fun unhideGeneralForumTopic(
        chatId: Long,
    ) = service.unhideGeneralForumTopic(chatId).getResult()

    suspend fun unpinAllGeneralForumTopicMessages(
        chatId: Long,
    ) = service.unpinAllGeneralForumTopicMessages(chatId).getResult()

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Int? = null,
    ) = service.answerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime).getResult()

    suspend fun getUserChatBoosts(
        chatId: Long,
        userId: Long,
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
        chatId: Long? = null,
        menuButton: MenuButton? = null,
    ) = service.setChatMenuButton(chatId, menuButton).getResult()

    suspend fun getChatMenuButton(
        chatId: Long? = null,
    ) = service.getChatMenuButton(chatId).getResult(bot)

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ) = service.setMyDefaultAdministratorRights(rights, forChannels).getResult()

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ) = service.getMyDefaultAdministratorRights(forChannels).getResult(bot)

    suspend fun editMessageText(
        chatId: Long? = null,
        messageId: Int? = null,
        text: String,
        inlineMessageId: String? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.editMessageText(chatId, messageId, inlineMessageId, text, parseMode?.mode, entities?.toTelegramList(), linkPreviewOptions, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun editMessageCaption(
        chatId: Long? = null,
        messageId: Int? = null,
        caption: String,
        inlineMessageId: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.editMessageCaption(chatId, messageId, inlineMessageId, caption, parseMode?.mode, captionEntities?.toTelegramList(), showCaptionAboveMedia, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun editMessageMedia(
        chatId: Long? = null,
        messageId: Int? = null,
        media: InputMedia,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.editMessageMedia(chatId, messageId, inlineMessageId, mediaGroupBodyFactory.createMediaGroupBody(listOf(media)), replyMarkup, businessConnectionId).getResult(bot)

    suspend fun editMessageReplyMarkup(
        chatId: Long? = null,
        messageId: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inlineMessageId: String? = null,
        businessConnectionId: String? = null,
    ) = service.editMessageReplyMarkup(chatId, messageId, inlineMessageId, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun stopPoll(
        chatId: Long,
        messageId: Int,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.stopPoll(chatId, messageId, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun deleteMessage(
        chatId: Long,
        messageId: Int,
    ) = service.deleteMessage(chatId, messageId).getResult()

    suspend fun deleteMessages(
        chatId: Long,
        messageIds: List<Int>,
    ) = service.deleteMessages(chatId, messageIds.toTelegramList()).getResult()

    suspend fun editMessageLiveLocation(
        chatId: Long? = null,
        messageId: Int? = null,
        latitude: Float,
        longitude: Float,
        inlineMessageId: String? = null,
        livePeriod: Int? = null,
        horizontalAccuracy: Float? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.editMessageLiveLocation(chatId, messageId, inlineMessageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun stopMessageLiveLocation(
        chatId: Long? = null,
        messageId: Int? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        businessConnectionId: String? = null,
    ) = service.stopMessageLiveLocation(chatId, messageId, inlineMessageId, replyMarkup, businessConnectionId).getResult(bot)

    suspend fun sendInvoice(
        chatId: Long,
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: List<LabeledPrice>,
        providerToken: String? = null,
        messageThreadId: String? = null,
        maxTipAmount: Int? = null,
        suggestedTipAmounts: List<Int>? = null,
        startParameter: String? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = service.sendInvoice(chatId, messageThreadId, title, description, payload, providerToken, currency, prices.toTelegramList(), maxTipAmount, suggestedTipAmounts?.toTelegramList(), startParameter, providerData, photoUrl, photoSize, photoWidth, photoHeight, needName, needPhoneNumber, needEmail, needShippingAddress, sendPhoneNumberToProvider, sendEmailToProvider, isFlexible, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup).getResult(bot)

    suspend fun createInvoiceLink(
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: List<LabeledPrice>,
        providerToken: String? = null,
        maxTipAmount: Int? = null,
        suggestedTipAmounts: List<Int>? = null,
        startParameter: String? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
    ) = service.createInvoiceLink(title, description, payload, providerToken, currency, prices.toTelegramList(), maxTipAmount, suggestedTipAmounts?.toTelegramList(), startParameter, providerData, photoUrl, photoSize, photoWidth, photoHeight, needName, needPhoneNumber, needEmail, needShippingAddress, sendPhoneNumberToProvider, sendEmailToProvider, isFlexible).getResult()

    suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>? = null,
        errorMessage: String? = null,
    ) = service.answerShippingQuery(shippingQueryId, ok, shippingOptions?.toTelegramList(), errorMessage).getResult()

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String? = null,
    ) = service.answerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage).getResult()

    suspend fun refundStarsPayment(
        userId: Long,
        telegramPaymentChargeId: String,
    ) = service.refundStarPayment(userId, telegramPaymentChargeId).getResult()

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        button: InlineQueryResultsButton? = null,
    ) = service.answerInlineQuery(inlineQueryId, results.toTelegramList(), cacheTime, isPersonal, nextOffset, button).getResult()

    suspend fun answerWebAppQuery(
        webAppQueryId: String,
        result: InlineQueryResult,
    ) = service.answerWebAppQuery(webAppQueryId, result).getResult(bot)

    suspend fun getStarTransactions(
        offset: Int,
        limit: Int? = 100,
    ) = service.getStarTransactions(offset, limit).getResult(bot)
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