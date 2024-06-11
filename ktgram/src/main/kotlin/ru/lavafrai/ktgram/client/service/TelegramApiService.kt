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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import ru.lavafrai.ktgram.exceptions.TelegramBadRequest
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.media.LinkPreviewOptions
import ru.lavafrai.ktgram.types.media.MessageEntity


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
        @Part("photo") photo: MultipartBody.Part,
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


fun productionTelegramApiService(token: String): TelegramApiService {
    val tolerantJson = Json {
        ignoreUnknownKeys = true
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(TgInterceptor())
        .build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(PRODUCTION.replace("{token}", token))
        .addConverterFactory(tolerantJson.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(TelegramApiService::class.java)
}
