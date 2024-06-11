package ru.lavafrai.ktgram.client.service

import ReplyParameters
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.*
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.media.File
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity
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
        @Query("allowed_updates") allowedUpdates: List<String>?,
    ): TelegramResult<List<Update>>

    @FormUrlEncoded
    @POST("sendMessage")
    suspend fun sendMessage(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("text") text: String,
        @Field("parse_mode") parseMode: String?,
        @Field("entities") entities: List<MessageEntity>?,
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
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Int,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("message_id") messageId: Int,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("forwardMessages")
    suspend fun forwardMessages(
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Int,
        @Field("message_ids") messageIds: List<Int>,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
    ): TelegramResult<List<Int>>

    @FormUrlEncoded
    @POST("copyMessage")
    suspend fun copyMessage(
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Int,
        @Field("message_id") messageId: Int,
        @Field("caption") caption: String? = null,
        @Field("parse_mode") parseMode: String? = null,
        @Field("caption_entities") captionEntities: List<MessageEntity>? = null,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean? = null,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("reply_parameters") replyParameters: ReplyParameters? = null,
        @Field("reply_markup") replyMarkup: ReplyMarkup? = null,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("copyMessages")
    suspend fun copyMessages(
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int? = null,
        @Field("from_chat_id") fromChatId: Int,
        @Field("message_ids") messageIds: List<Int>,
        @Field("disable_notification") disableNotification: Boolean? = null,
        @Field("protect_content") protectContent: Boolean? = null,
        @Field("remove_caption") removeCaption: Boolean? = null,
    ): TelegramResult<List<Int>>

    @Multipart
    @POST("sendPhoto")
    suspend fun sendPhoto(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part photo: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendPhoto")
    suspend fun sendPhoto(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("photo") photo: String,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Field("has_spoiler") hasSpoiler: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendAudio")
    suspend fun sendAudio(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part audio: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
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

    @FormUrlEncoded
    @POST("sendAudio")
    suspend fun sendAudio(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("audio") audio: String,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("duration") duration: Int?,
        @Field("performer") performer: String?,
        @Field("title") title: String?,
        @Field("thumbnail") thumbnail: String?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendDocument")
    suspend fun sendDocument(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part document: MultipartBody.Part,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
        @Part("disable_content_type_detection") disableContentTypeDetection: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendDocument")
    suspend fun sendDocument(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("document") document: String,
        @Field("thumbnail") thumbnail: String?,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("disable_content_type_detection") disableContentTypeDetection: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVideo")
    suspend fun sendVideo(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part video: MultipartBody.Part,
        @Part("duration") duration: Int?,
        @Part("width") width: Int?,
        @Part("height") height: Int?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("supports_streaming") supportsStreaming: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendVideo")
    suspend fun sendVideo(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("video") video: String,
        @Field("duration") duration: Int?,
        @Field("width") width: Int?,
        @Field("height") height: Int?,
        @Field("thumbnail") thumbnail: String?,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Field("has_spoiler") hasSpoiler: Boolean?,
        @Field("supports_streaming") supportsStreaming: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendAnimation")
    suspend fun sendAnimation(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part animation: MultipartBody.Part,
        @Part("duration") duration: Int?,
        @Part("width") width: Int?,
        @Part("height") height: Int?,
        @Part thumbnail: MultipartBody.Part?,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
        @Part("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Part("has_spoiler") hasSpoiler: Boolean?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendAnimation")
    suspend fun sendAnimation(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("animation") animation: String,
        @Field("duration") duration: Int?,
        @Field("width") width: Int?,
        @Field("height") height: Int?,
        @Field("thumbnail") thumbnail: String?,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("show_caption_above_media") showCaptionAboveMedia: Boolean?,
        @Field("has_spoiler") hasSpoiler: Boolean?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVoice")
    suspend fun sendVoice(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
        @Part("message_thread_id") messageThreadId: Int?,
        @Part voice: MultipartBody.Part,
        @Part("caption") caption: String?,
        @Part("parse_mode") parseMode: String?,
        @Part("caption_entities") captionEntities: List<MessageEntity>?,
        @Part("duration") duration: Int?,
        @Part("disable_notification") disableNotification: Boolean?,
        @Part("protect_content") protectContent: Boolean?,
        @Part("message_effect_id") messageEffectId: String?,
        @Part("reply_parameters") replyParameters: ReplyParameters?,
        @Part("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @FormUrlEncoded
    @POST("sendVoice")
    suspend fun sendVoice(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("voice") voice: String,
        @Field("caption") caption: String?,
        @Field("parse_mode") parseMode: String?,
        @Field("caption_entities") captionEntities: List<MessageEntity>?,
        @Field("duration") duration: Int?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>

    @Multipart
    @POST("sendVideoNote")
    suspend fun sendVideoNote(
        @Part("business_connection_id") businessConnectionId: String?,
        @Part("chat_id") chatId: Int,
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

    @FormUrlEncoded
    @POST("sendVideoNote")
    suspend fun sendVideoNote(
        @Field("business_connection_id") businessConnectionId: String?,
        @Field("chat_id") chatId: Int,
        @Field("message_thread_id") messageThreadId: Int?,
        @Field("video_note") videoNote: String,
        @Field("duration") duration: Int?,
        @Field("length") length: Int?,
        @Field("thumbnail") thumbnail: String?,
        @Field("disable_notification") disableNotification: Boolean?,
        @Field("protect_content") protectContent: Boolean?,
        @Field("message_effect_id") messageEffectId: String?,
        @Field("reply_parameters") replyParameters: ReplyParameters?,
        @Field("reply_markup") replyMarkup: ReplyMarkup?,
    ): TelegramResult<Message>
}

const val PRODUCTION = "https://api.telegram.org/bot{token}/"


class TgInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response = chain.proceed(request)
        if (response.code() == 400) {
            throw TelegramBadRequest(response.body()?.string() ?: "")
        }

        return response
    }
}


val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(TgInterceptor())
    .callTimeout(10.minutes.toJavaDuration())
    .readTimeout(10.minutes.toJavaDuration())
    .writeTimeout(10.minutes.toJavaDuration())
    .build()

fun productionTelegramApiService(token: String): TelegramApiService {
    val tolerantJson = Json {
        ignoreUnknownKeys = true
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
