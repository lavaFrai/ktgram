package ru.lavafrai.ktgram.client.service

import BotCommand
import BotDescription
import BotName
import BotShortDescription
import ChatAdministratorRights
import ChatFullInfo
import ChatInviteLink
import ChatPermissions
import ForumTopic
import ReactionType
import ReplyParameters
import UserChatBoosts
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import ktgram.types.stickers.Sticker
import okhttp3.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.*
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.business.BusinessConnection
import ru.lavafrai.ktgram.types.inline.SentWebAppMessage
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult
import ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResultsButton
import ru.lavafrai.ktgram.types.media.File
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.media.UserProfilePhotos
import ru.lavafrai.ktgram.types.payments.LabeledPrice
import ru.lavafrai.ktgram.types.payments.ShippingOption
import ru.lavafrai.ktgram.types.poll.InputPollOption
import ru.lavafrai.ktgram.types.poll.Poll
import ru.lavafrai.ktgram.types.replymarkup.ReplyMarkup
import java.net.InetSocketAddress
import java.net.Proxy
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toJavaDuration


interface TelegramApiService {
    @POST("getMe")
    suspend fun getMe(): TelegramResult<User>

    @POST("getUpdates")
    suspend fun getUpdates(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
        @Query("timeout") timeout: Int?,
        @Query("allowed_updates") allowedUpdates: TelegramList<String>?,
    ): TelegramResult<List<Update>>

    @FormUrlEncoded
    @POST("sendMessage")
    suspend fun sendMessage(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("text") text: String,
        @Field("parse_mode") parseMode: String?,
        @Field("entities") entities: TelegramList<MessageEntity>?,
        @Field("link_preview_options") linkPreviewOptions: LinkPreviewOptions?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("getFile")
    suspend fun getFile(
        @Field("file_id") fileId: String
    ): TelegramResult<File>

    @FormUrlEncoded
    @POST("forwardMessage")
    suspend fun forwardMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Long,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("message_id") messageId: Int,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("forwardMessages")
    suspend fun forwardMessages(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Long,
        @Field("message_ids") messageIds: TelegramList<Int>,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
    ): TelegramResult<List<Int>>

    @FormUrlEncoded
    @POST("copyMessage")
    suspend fun copyMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Long,
        @Field("message_id") messageId: Int,
        @Field("caption") caption: String? = null,
        @Field("parse_mode") parseMode: String? = null,
        @Field("caption_entities") captionEntities: TelegramList<MessageEntity>? = null,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean? = null,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("reply_parameters") replyParameters: ReplyParameters? = null,
        @Field("reply_markup") replyMarkup: ReplyMarkup? = null,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("copyMessages")
    suspend fun copyMessages(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Long,
        @Field("message_ids") messageIds: TelegramList<Int>,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("remove_caption") removeCaption: Boolean? = null,
    ): TelegramResult<List<Int>>

    @Multipart
    @POST("sendPhoto")
    suspend fun sendPhoto(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part photo: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part audio: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("duration") duration: Int?,
        @Part("performer") performer: String?,
        @Part("title") title: String?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendDocument")
    suspend fun sendDocument(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part document: MultipartBody.Part,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("disable_content_type_detection") disableContentTypeDetection: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVideo")
    suspend fun sendVideo(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part video: MultipartBody.Part,
        @Part("duration") duration: Int?,
        @Part("width") width: Int?,
        @Part("height") height: Int?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("supports_streaming") supportsStreaming: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendAnimation")
    suspend fun sendAnimation(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part animation: MultipartBody.Part,
        @Part("duration") duration: Int?,
        @Part("width") width: Int?,
        @Part("height") height: Int?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVoice")
    suspend fun sendVoice(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part voice: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Part("duration") duration: Int?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVideoNote")
    suspend fun sendVideoNote(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part videoNote: MultipartBody.Part,
        @Part("duration") duration: Int?,
        @Part("length") length: Int?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendMediaGroup")
    suspend fun sendMediaGroup(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Long,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part media: List<MultipartBody.Part>,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
    ): TelegramResult<List<Message>>

    @FormUrlEncoded
    @POST("sendLocation")
    suspend fun sendLocation(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("latitude") latitude: Float,
        @Field("longitude") longitude: Float,
        @Field("horizontal_accuracy") horizontalAccuracy: Float?,
        @Field("live_period") livePeriod: Int?,
        @Field("heading") heading: Int?,
        @Field("proximity_alert_radius") proximityAlertRadius: Int?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendVenue")
    suspend fun sendVenue(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("latitude") latitude: Float,
        @Field("longitude") longitude: Float,
        @Field("title") title: String,
        @Field("address") address: String,
        @Field("foursquare_id") foursquareId: String?,
        @Field("foursquare_type") foursquareType: String?,
        @Field("google_place_id") googlePlaceId: String?,
        @Field("google_place_type") googlePlaceType: String?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendContact")
    suspend fun sendContact(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("phone_number") phoneNumber: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String?,
        @Field("vcard") vcard: String?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendPoll")
    suspend fun sendPoll(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("question") question: String,
        @Field("question_parse_mode") questionParseMode: String?,
        @Field("question_entities") questionEntities: TelegramList<MessageEntity>?,
        @Field("options") options: TelegramList<InputPollOption>,
        @Field("is_anonymous") isAnonymous: Boolean?,
        @Field("type") type: String?,
        @Field("allows_multiple_answers") allowsMultipleAnswers: Boolean?,
        @Field("correct_option_id") correctOptionId: Int?,
        @Field("explanation") explanation: String?,
        @Field("explanation_parse_mode") explanationParseMode: String?,
        @Field("explanation_entities") explanationEntities: TelegramList<MessageEntity>?,
        @Field("open_period") openPeriod: Int?,
        @Field("close_date") closeDate: Int?,
        @Field("is_closed") isClosed: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendDice")
    suspend fun sendDice(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("emoji") emoji: String?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendChatAction")
    suspend fun sendChatAction(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("action") action: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("setMessageReaction")
    suspend fun setMessageReaction(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Int,
        @Field("reaction") reaction: TelegramList<ReactionType>?,
        @Field("is_big") isBig: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getUserProfilePhotos")
    suspend fun getUserProfilePhotos(
        @Field("user_id") userId: Long,
        @Field("offset") offset: Int?,
        @Field("limit") limit: Int?,
    ): TelegramResult<UserProfilePhotos>

    @FormUrlEncoded
    @POST("banChatMember")
    suspend fun banChatMember(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
        @Field("until_date") untilDate: Int?,
        @Field("revoke_messages") revokeMessages: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unbanChatMember")
    suspend fun unbanChatMember(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
        @Field("only_if_banned") onlyIfBanned: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("restrictChatMember")
    suspend fun restrictChatMember(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
        @Field("permissions") permissions: ChatPermissions,
        @Field("use_independent_chat_permissions") useIndependentChatPermissions: Boolean?,
        @Field("until_date") untilDate: Int?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("promoteChatMember")
    suspend fun promoteChatMember(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
        @Field("is_anonymous") isAnonymous: Boolean?,
        @Field("can_manage_chat") canManageChat: Boolean?,
        @Field("can_delete_messages") canDeleteMessages: Boolean?,
        @Field("can_manage_video_chats") canManageVideoChats: Boolean?,
        @Field("can_restrict_members") canRestrictMembers: Boolean?,
        @Field("can_promote_members") canPromoteMembers: Boolean?,
        @Field("can_change_info") canChangeInfo: Boolean?,
        @Field("can_invite_users") canInviteUsers: Boolean?,
        @Field("can_post_stories") canPostStories: Boolean?,
        @Field("can_edit_stories") canEditStories: Boolean?,
        @Field("can_delete_stories") canDeleteStories: Boolean?,
        @Field("can_post_messages") canPostMessages: Boolean?,
        @Field("can_edit_messages") canEditMessages: Boolean?,
        @Field("can_pin_messages") canPinMessages: Boolean?,
        @Field("can_manage_topics") canManageTopics: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("setChatAdministratorCustomTitle")
    suspend fun setChatAdministratorCustomTitle(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
        @Field("custom_title") customTitle: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("banChatSenderChat")
    suspend fun banChatSenderChat(
        @Field("chat_id") chatId: Long,
        @Field("sender_chat_id") senderChatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unbanChatSenderChat")
    suspend fun unbanChatSenderChat(
        @Field("chat_id") chatId: Long,
        @Field("sender_chat_id") senderChatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("setChatPermissions")
    suspend fun setChatPermissions(
        @Field("chat_id") chatId: Long,
        @Field("permissions") permissions: ChatPermissions,
        @Field("use_independent_chat_permissions") useIndependentChatPermissions: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    suspend fun exportChatInviteLink(
        @Field("chat_id") chatId: Long
    ): TelegramResult<String>

    @FormUrlEncoded
    @POST("createChatInviteLink")
    suspend fun createChatInviteLink(
        @Field("chat_id") chatId: Long,
        @Field("name") name: String?,
        @Field("expire_date") expireDate: Int?,
        @Field("member_limit") memberLimit: Int?,
        @Field("creates_join_request") createsJoinRequest: Boolean?,
    ): TelegramResult<ChatInviteLink>

    @FormUrlEncoded
    @POST("editChatInviteLink")
    suspend fun editChatInviteLink(
        @Field("chat_id") chatId: Long,
        @Field("invite_link") inviteLink: String,
        @Field("name") name: String?,
        @Field("expire_date") expireDate: Int?,
        @Field("member_limit") memberLimit: Int?,
        @Field("creates_join_request") createsJoinRequest: Boolean?,
    ): TelegramResult<ChatInviteLink>

    @FormUrlEncoded
    @POST("revokeChatInviteLink")
    suspend fun revokeChatInviteLink(
        @Field("chat_id") chatId: Long,
        @Field("invite_link") inviteLink: String,
    ): TelegramResult<ChatInviteLink>

    @FormUrlEncoded
    @POST("approveChatJoinRequest")
    suspend fun approveChatJoinRequest(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("declineChatJoinRequest")
    suspend fun declineChatJoinRequest(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
    ): TelegramResult<Boolean>

    @Multipart
    @POST("setChatPhoto")
    suspend fun setChatPhoto(
        @Part("chat_id") chatId: Long,
        @Part photo: MultipartBody.Part,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    suspend fun deleteChatPhoto(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("setChatTitle")
    suspend fun setChatTitle(
        @Field("chat_id") chatId: Long,
        @Field("title") title: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("setChatDescription")
    suspend fun setChatDescription(
        @Field("chat_id") chatId: Long,
        @Field("description") description: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("pinChatMessage")
    suspend fun pinChatMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Int,
        @Field("disable_notification") disableNotification: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unpinChatMessage")
    suspend fun unpinChatMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Int?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unpinAllChatMessages")
    suspend fun unpinAllChatMessages(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("leaveChat")
    suspend fun leaveChat(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getChat")
    suspend fun getChat(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<ChatFullInfo>

    @FormUrlEncoded
    @POST("getChatAdministrators")
    suspend fun getChatAdministrators(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<List<ChatMember>>

    @FormUrlEncoded
    @POST("getChatMemberCount")
    suspend fun getChatMemberCount(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Int>

    @FormUrlEncoded
    @POST("getChatMember")
    suspend fun getChatMember(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
    ): TelegramResult<ChatMember>

    @FormUrlEncoded
    @POST("setChatStickerSet")
    suspend fun setChatStickerSet(
        @Field("chat_id") chatId: Long,
        @Field("sticker_set_name") stickerSetName: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("deleteChatStickerSet")
    suspend fun deleteChatStickerSet(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @POST("getForumTopicIconStickers")
    suspend fun getForumTopicIconStickers(): TelegramResult<List<Sticker>>

    @FormUrlEncoded
    @POST("createForumTopic")
    suspend fun createForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("name") name: String,
        @Field("icon_color") iconColor: Int?,
        @Field("icon_custom_emoji_id") iconCustomEmojiId: String?,
    ): TelegramResult<ForumTopic>

    @FormUrlEncoded
    @POST("editForumTopic")
    suspend fun editForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int,
        @Field("name") name: String?,
        @Field("icon_custom_emoji_id") iconCustomEmojiId: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("closeForumTopic")
    suspend fun closeForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("reopenForumTopic")
    suspend fun reopenForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("deleteForumTopic")
    suspend fun deleteForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unpinAllForumTopicMessages")
    suspend fun unpinAllForumTopicMessages(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: Int,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("editGeneralForumTopic")
    suspend fun editGeneralForumTopic(
        @Field("chat_id") chatId: Long,
        @Field("name") name: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("closeGeneralForumTopic")
    suspend fun closeGeneralForumTopic(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("reopenGeneralForumTopic")
    suspend fun reopenGeneralForumTopic(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("hideGeneralForumTopic")
    suspend fun hideGeneralForumTopic(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unhideGeneralForumTopic")
    suspend fun unhideGeneralForumTopic(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("unpinAllGeneralForumTopicMessages")
    suspend fun unpinAllGeneralForumTopicMessages(
        @Field("chat_id") chatId: Long,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("answerCallbackQuery")
    suspend fun answerCallbackQuery(
        @Field("callback_query_id") callbackQueryId: String,
        @Field("text") text: String?,
        @Field("show_alert") showAlert: Boolean?,
        @Field("url") url: String?,
        @Field("cache_time") cacheTime: Int?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getUserChatBoosts")
    suspend fun getUserChatBoosts(
        @Field("chat_id") chatId: Long,
        @Field("user_id") userId: Long,
    ): TelegramResult<UserChatBoosts>

    @FormUrlEncoded
    @POST("getBusinessConnection")
    suspend fun getBusinessConnection(
        @Field("business_connection_id") businessConnectionId: String,
    ): TelegramResult<BusinessConnection>

    @FormUrlEncoded
    @POST("setMyCommands")
    suspend fun setMyCommands(
        @Field("commands") commands: TelegramList<BotCommand>,
        @Field("scope") scope: BotCommandScope?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("deleteMyCommands")
    suspend fun deleteMyCommands(
        @Field("scope") scope: BotCommandScope?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getMyCommands")
    suspend fun getMyCommands(
        @Field("scope") scope: BotCommandScope?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<List<BotCommand>>

    @FormUrlEncoded
    @POST("setMyName")
    suspend fun setMyName(
        @Field("name") name: String?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getMyName")
    suspend fun getMyName(
        @Field("language_code") languageCode: String?,
    ): TelegramResult<BotName>

    @FormUrlEncoded
    @POST("setMyDescription")
    suspend fun setMyDescription(
        @Field("description") description: String?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getMyDescription")
    suspend fun getMyDescription(
        @Field("language_code") languageCode: String?,
    ): TelegramResult<BotDescription>

    @FormUrlEncoded
    @POST("setMyShortDescription")
    suspend fun setMyShortDescription(
        @Field("short_description") shortDescription: String?,
        @Field("language_code") languageCode: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getMyShortDescription")
    suspend fun getMyShortDescription(
        @Field("language_code") languageCode: String?,
    ): TelegramResult<BotShortDescription>

    @FormUrlEncoded
    @POST("setChatMenuButton")
    suspend fun setChatMenuButton(
        @Field("chat_id") chatId: Long?,
        @Field("menu_button") menuButton: MenuButton?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getChatMenuButton")
    suspend fun getChatMenuButton(
        @Field("chat_id") chatId: Long?,
    ): TelegramResult<MenuButton>

    @FormUrlEncoded
    @POST("setMyDefaultAdministratorRights")
    suspend fun setMyDefaultAdministratorRights(
        @Field("rights") rights: ChatAdministratorRights?,
        @Field("for_channels") forChannels: Boolean?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("getMyDefaultAdministratorRights")
    suspend fun getMyDefaultAdministratorRights(
        @Field("for_channels") forChannels: Boolean?,
    ): TelegramResult<ChatAdministratorRights>

    @FormUrlEncoded
    @POST("editMessageText")
    suspend fun editMessageText(
        @Field("chat_id") chatId: Long?,
        @Field("message_id") messageId: Int?,
        @Field("inline_message_id") inlineMessageId: String?,
        @Field("text") text: String,
        @Field("parse_mode") parseMode: String?,
        @Field("entities") entities: TelegramList<MessageEntity>?,
        @Field("link_preview_options") linkPreviewOptions: LinkPreviewOptions?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("editMessageCaption")
    suspend fun editMessageCaption(
        @Field("chat_id") chatId: Long?,
        @Field("message_id") messageId: Int?,
        @Field("inline_message_id") inlineMessageId: String?,
        @Field("caption") caption: String,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: TelegramList<MessageEntity>?,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("editMessageMedia")
    suspend fun editMessageMedia(
        @Part("chat_id") chatId: Long?,
        @Part("message_id") messageId: Int?,
        @Part("inline_message_id") inlineMessageId: String?,
        @Part media: List<MultipartBody.Part>,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("editMessageLiveLocation")
    suspend fun editMessageLiveLocation(
        @Field("chat_id") chatId: Long?,
        @Field("message_id") messageId: Int?,
        @Field("inline_message_id") inlineMessageId: String?,
        @Field("latitude") latitude: Float,
        @Field("longitude") longitude: Float,
        @Field("live_period") livePeriod: Int?,
        @Field("horizontal_accuracy") horizontalAccuracy: Float?,
        @Field("heading") heading: Int?,
        @Field("proximity_alert_radius") proximityAlertRadius: Int?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("stopMessageLiveLocation")
    suspend fun stopMessageLiveLocation(
        @Field("chat_id") chatId: Long?,
        @Field("message_id") messageId: Int?,
        @Field("inline_message_id") inlineMessageId: String?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("editMessageReplyMarkup")
    suspend fun editMessageReplyMarkup(
        @Field("chat_id") chatId: Long?,
        @Field("message_id") messageId: Int?,
        @Field("inline_message_id") inlineMessageId: String?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("stopPoll")
    suspend fun stopPoll(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Int,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Poll>

    @FormUrlEncoded
    @POST("deleteMessage")
    suspend fun deleteMessage(
        @Field("chat_id") chatId: Long,
        @Field("message_id") messageId: Int,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("deleteMessages")
    suspend fun deleteMessages(
        @Field("chat_id") chatId: Long,
        @Field("message_ids") messageIds: TelegramList<Int>,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("sendInvoice")
    suspend fun sendInvoice(
        @Field("chat_id") chatId: Long,
        @Field("message_thread_id") messageThreadId: String?,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("payload") payload: String,
        @Field("provider_token") providerToken: String?,
        @Field("currency") currency: String,
        @Field("prices") prices: TelegramList<LabeledPrice>,
        @Field("max_tip_amount") maxTipAmount: Int?,
        @Field("suggested_tip_amounts") suggestedTipAmounts: TelegramList<Int>?,
        @Field("start_parameter") startParameter: String?,
        @Field("provider_data") providerData: String?,
        @Field("photo_url") photoUrl: String?,
        @Field("photo_size") photoSize: Int?,
        @Field("photo_width") photoWidth: Int?,
        @Field("photo_height") photoHeight: Int?,
        @Field("need_name") needName: Boolean?,
        @Field("need_phone_number") needPhoneNumber: Boolean?,
        @Field("need_email") needEmail: Boolean?,
        @Field("need_shipping_address") needShippingAddress: Boolean?,
        @Field("send_phone_number_to_provider") sendPhoneNumberToProvider: Boolean?,
        @Field("send_email_to_provider") sendEmailToProvider: Boolean?,
        @Field("is_flexible") isFlexible: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("createInvoiceLink")
    suspend fun createInvoiceLink(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("payload") payload: String,
        @Field("provider_token") providerToken: String?,
        @Field("currency") currency: String,
        @Field("prices") prices: TelegramList<LabeledPrice>,
        @Field("max_tip_amount") maxTipAmount: Int?,
        @Field("suggested_tip_amounts") suggestedTipAmounts: TelegramList<Int>?,
        @Field("start_parameter") startParameter: String?,
        @Field("provider_data") providerData: String?,
        @Field("photo_url") photoUrl: String?,
        @Field("photo_size") photoSize: Int?,
        @Field("photo_width") photoWidth: Int?,
        @Field("photo_height") photoHeight: Int?,
        @Field("need_name") needName: Boolean?,
        @Field("need_phone_number") needPhoneNumber: Boolean?,
        @Field("need_email") needEmail: Boolean?,
        @Field("need_shipping_address") needShippingAddress: Boolean?,
        @Field("send_phone_number_to_provider") sendPhoneNumberToProvider: Boolean?,
        @Field("send_email_to_provider") sendEmailToProvider: Boolean?,
        @Field("is_flexible") isFlexible: Boolean?,
    ): TelegramResult<String>

    @FormUrlEncoded
    @POST("answerShippingQuery")
    suspend fun answerShippingQuery(
        @Field("shipping_query_id") shippingQueryId: String,
        @Field("ok") ok: Boolean,
        @Field("shipping_options") shippingOptions: TelegramList<ShippingOption>?,
        @Field("error_message") errorMessage: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("answerPreCheckoutQuery")
    suspend fun answerPreCheckoutQuery(
        @Field("pre_checkout_query_id") preCheckoutQueryId: String,
        @Field("ok") ok: Boolean,
        @Field("error_message") errorMessage: String?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("refundStarPayment")
    suspend fun refundStarPayment(
        @Field("user_id") userId: Long,
        @Field("telegram_payment_charge_id") telegramPaymentChargeId: String,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("answerInlineQuery")
    suspend fun answerInlineQuery(
        @Field("inline_query_id") inlineQueryId: String,
        @Field("results") results: TelegramList<InlineQueryResult>,
        @Field("cache_time") cacheTime: Int?,
        @Field("is_personal") isPersonal: Boolean?,
        @Field("next_offset") nextOffset: String?,
        @Field("button") button: InlineQueryResultsButton?,
    ): TelegramResult<Boolean>

    @FormUrlEncoded
    @POST("answerWebAppQuery")
    suspend fun answerWebAppQuery(
        @Field("web_app_query_id") webAppQueryId: String,
        @Field("result") result: InlineQueryResult,
    ): TelegramResult<SentWebAppMessage>
}

const val PRODUCTION = "https://api.telegram.org/bot{token}/"


class TgInterceptor(val log: Logger = LoggerFactory.getLogger(TgInterceptor::class.java)): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response = chain.proceed(request)

        response = handleErrors(request, response, chain)

        return response
    }

    private fun handleErrors(request: Request, response: Response, chain: Interceptor.Chain): Response {
        if (response.code() == 400) {
            throw TelegramBadRequest(response.body()?.string() ?: "")
        }

        if (response.code() == 429) {
            val wait = 10000 + (Math.random() * 5000).toLong()
            log.warn("Throttling while processing request. Waiting for ${wait.div(1000)} seconds")
            Thread.sleep(wait)
            return redoRequest(request, response, chain)
        }

        return response
    }

    private fun redoRequest(req: Request, oldResp: Response, chain: Interceptor.Chain): Response {
        val newRequest: Request = req.newBuilder().build()
        oldResp.close()

        var response = chain.proceed(newRequest)
        response = handleErrors(newRequest, response, chain)

        return response;
    }
}


val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("127.0.0.1", 8080))

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(TgInterceptor())
    .callTimeout(10.minutes.toJavaDuration())
    .readTimeout(10.minutes.toJavaDuration())
    .writeTimeout(10.minutes.toJavaDuration())
    // .proxy(proxy)
    .build()

fun productionTelegramApiService(token: String, json: Json? = null): TelegramApiService {
    val tolerantJson = json ?: Json {
        ignoreUnknownKeys = true;
        encodeDefaults = true
    }

    val client = okHttpClient

    return Retrofit.Builder()
        .client(client)
        .baseUrl(PRODUCTION.replace("{token}", token))
        .addConverterFactory(tolerantJson.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(TelegramApiService::class.java)
}

fun TelegramApiService.getClient(): OkHttpClient {
    return okHttpClient
}
