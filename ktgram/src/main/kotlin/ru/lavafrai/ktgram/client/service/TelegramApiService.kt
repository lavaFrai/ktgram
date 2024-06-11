package ru.lavafrai.ktgram.client.service

import ReplyParameters
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
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
}

const val PRODUCTION = "https://api.telegram.org/bot{token}/"


class TgInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response = chain.proceed(request)
        if (response.code == 400) {
            throw TelegramBadRequest(response.body?.string() ?: "")
        }

        return response
    }
}


fun productionTelegramApiService(token: String): TelegramApiService {
    val tolerantJson = Json {
        ignoreUnknownKeys = true
    }

    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json = tolerantJson)
        }

        engine {
            addInterceptor(TgInterceptor())
        }
    }

    return Ktorfit.Builder()
        .httpClient(client)
        .baseUrl(PRODUCTION.replace("{token}", token))
        .build()
        .createTelegramApiService()
}
