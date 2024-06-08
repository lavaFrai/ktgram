package ru.lavafrai.ktgram.client.service

import ReplyParameters
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
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
