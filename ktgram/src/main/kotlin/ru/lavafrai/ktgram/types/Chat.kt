package ru.lavafrai.ktgram.types

import PollType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.DiceEmoji
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.media.inputmedia.InputMedia
import ru.lavafrai.ktgram.types.payments.LabeledPrice
import ru.lavafrai.ktgram.types.poll.InputPollOption
import ru.lavafrai.ktgram.types.replymarkup.ReplyMarkup


@Serializable
class Chat(
    val id: Long,
    val type: String,
    val title: String? = null,
    val username: String? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("is_forum") val isForum: Boolean? = false,
) : TelegramObject() {
    suspend fun ban(userId: Long, untilDate: Int? = null, revokeMessages: Boolean? = null) {
        bot.api.banChatMember(id, userId, untilDate, revokeMessages)
    }

    suspend fun unban(userId: Long, onlyIfBanned: Boolean? = null) {
        bot.api.unbanChatMember(id, userId, onlyIfBanned)
    }

    suspend fun promote(
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
    ) {
        bot.api.promoteChatMember(id, userId, isAnonymous, canManageChat, canDeleteMessages, canManageVideoChats, canRestrictMembers, canPromoteMembers, canChangeInfo, canInviteUsers, canPostStories, canEditStories, canDeleteStories, canPostMessages, canEditMessages, canPinMessages, canManageTopics)
    }

    suspend fun setPhoto(photo: InputFile) {
        bot.api.setChatPhoto(id, photo)
    }

    suspend fun deletePhoto() {
        bot.api.deleteChatPhoto(id)
    }

    suspend fun setTitle(title: String) {
        bot.api.setChatTitle(id, title)
    }

    suspend fun pin(messageId: Int) {
        bot.api.pinChatMessage(id, messageId)
    }

    suspend fun unpin(messageId: Int) {
        bot.api.unpinChatMessage(id, messageId)
    }

    suspend fun unpinAll() {
        bot.api.unpinAllChatMessages(id)
    }

    suspend fun leave() {
        bot.api.leaveChat(id)
    }

    suspend fun getFullInfo() {
        bot.api.getChat(id)
    }

    suspend fun sendText(
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message {
        return bot.sendMessage(
            id,
            text,
            parseMode = parseMode,
            entities = entities,
            disableNotification = disableNotification,
            protectContent = protectContent,
            messageEffectId = messageEffectId,
            replyMarkup = replyMarkup)
    }

    suspend fun sendPhoto(
        photo: InputFile,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendPhoto(
        id,
        photo,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendAudio(
        audio: InputFile,
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
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendAudio(
        id,
        audio,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendDocument(
        document: InputFile,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendDocument(
        id,
        document,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendVideo(
        video: InputFile,
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
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendVideo(
        id,
        video,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendAnimation(
        animation: InputFile,
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
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendAnimation(
        id,
        animation,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendVoice(
        voice: InputFile,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendVoice(
        id,
        voice,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendVideoNote(
        videoNote: InputFile,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: InputFile? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendVideoNote(
        id,
        videoNote,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendMediaGroup(
        media: List<InputMedia>,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<Message> = bot.sendMediaGroup(
        id,
        media,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    suspend fun sendInvoice(
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
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendInvoice(
        id,
        title,
        description,
        payload,
        currency,
        prices,
        providerToken = providerToken,
        maxTipAmount = maxTipAmount,
        suggestedTipAmounts = suggestedTipAmounts,
        startParameter = startParameter,
        providerData = providerData,
        photoUrl = photoUrl,
        photoSize = photoSize,
        photoWidth = photoWidth,
        photoHeight = photoHeight,
        needName = needName,
        needPhoneNumber = needPhoneNumber,
        needEmail = needEmail,
        needShippingAddress = needShippingAddress,
        sendPhoneNumberToProvider = sendPhoneNumberToProvider,
        sendEmailToProvider = sendEmailToProvider,
        isFlexible = isFlexible,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        livePeriod: Int? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendLocation(
        id,
        latitude,
        longitude,
        horizontalAccuracy = horizontalAccuracy,
        livePeriod = livePeriod,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendVenue(
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        foursquareId: String? = null,
        foursquareType: String? = null,
        googlePlaceId: String? = null,
        googlePlaceType: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendVenue(
        id,
        latitude,
        longitude,
        title,
        address,
        foursquareId = foursquareId,
        foursquareType = foursquareType,
        googlePlaceId = googlePlaceId,
        googlePlaceType = googlePlaceType,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendContact(
        phoneNumber: String,
        firstName: String,
        lastName: String? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendContact(
        id,
        phoneNumber,
        firstName,
        lastName = lastName,
        vcard = vcard,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendDice(
        emoji: DiceEmoji,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendDice(
        id,
        emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )

    suspend fun sendPoll(
        question: String,
        options: List<String>,
        isAnonymous: Boolean? = null,
        type: PollType? = null,
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
        replyMarkup: ReplyMarkup? = null,
    ): Message = bot.sendPoll(
        id,
        question,
        options.map { InputPollOption(it) },
        isAnonymous = isAnonymous,
        type = type?.type,
        allowsMultipleAnswers = allowsMultipleAnswers,
        correctOptionId = correctOptionId,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        explanationEntities = explanationEntities,
        openPeriod = openPeriod,
        closeDate = closeDate,
        isClosed = isClosed,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyMarkup = replyMarkup,
    )
}
