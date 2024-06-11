package ru.lavafrai.ktgram.client

import ReplyParameters
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import org.slf4j.LoggerFactory
import ru.gildor.coroutines.okhttp.await
import ru.lavafrai.ktgram.client.eventProvider.UpdateProvider
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.client.service.getClient
import ru.lavafrai.ktgram.client.service.productionTelegramApiService
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.exceptions.KtgramExceptions
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.utils.extractBotId
import ru.lavafrai.ktgram.utils.validateToken
import java.util.concurrent.atomic.AtomicBoolean

class Bot (
    token: String,
    session: TelegramApiService? = null,
    default: DefaultBotProperties? = null,
) {
    private val stopSignal = AtomicBoolean(false)
    private val _token: String
    private var service: TelegramApiService
    private var default: DefaultBotProperties
    val api: TelegramApi
    private lateinit var me: User
    private val logger = LoggerFactory.getLogger(Bot::class.java)
    private val updateFlow: Flow<Update>
    private val handlerScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    val dispatcher = Dispatcher(this)

    init {
        validateToken(token)

        this._token = token
        this.service = session ?: productionTelegramApiService(token)
        this.default = default ?: DefaultBotProperties()
        this.api = TelegramApi(this, service)
        this.updateFlow = UpdateProvider(this, this.default.timeout, stopSignal).getUpdatesFlow()
    }

    suspend fun startPolling() {
        me = getMe()
        logger.info("Logged in as ${me.firstName}")

        updateFlow.collect {
            handlerScope.launch {
                handleUpdate(it)
            }
        }
    }

    fun runPolling() {
        runBlocking {
            startPolling()
            return@runBlocking
        }
    }

    suspend fun stopPolling() {
        stopSignal.set(true)
        logger.info("Polling stopping by bot id=${me.id}")
    }

    private suspend fun handleUpdate(update: Update) {
        val handleStartTime = System.currentTimeMillis()
        var handled = false
        try {
            handled = dispatcher.handleUpdate(update)
        } catch (e: Exception) {
            val handleEndTime = System.currentTimeMillis()
            logger.error("Error while handling update id=${update.updateId}")
            e.printStackTrace()
            return
        }

        val handleEndTime = System.currentTimeMillis()
        if (handled) logger.info("Update id=${update.updateId} type=${update.type} is handled. Duration ${handleEndTime - handleStartTime} ms by bot id=${me.id}")
        else logger.info("Update id=${update.updateId} type=${update.type} is not handled by bot id=${me.id}")
    }

    /**
     * Get bot id from token
     */
    val id: Long
        get() = extractBotId(token)

    val token: String
        get() = this._token

    suspend fun getMe(): User {
        return api.getMe()
    }

    suspend fun downloadFileToBytes(fileId: String): ByteArray {
        val file = getFile(fileId)

        val client = service.getClient()
        val request = Request.Builder()
            .url("https://api.telegram.org/file/bot$token/${file.filePath}")
            .get()
            .build()

        val response = client.newCall(request).await()
        return response.body()!!.bytes()
    }

    /**
     * Use this method to send text messages. On success, the sent Message is returned.
     */
    suspend fun sendMessage(
        chatId: Int,
        text: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        parseMode: String? = default.parseMode,
        entities: List<ru.lavafrai.ktgram.types.media.MessageEntity>? = null,
        linkPreviewOptions: ru.lavafrai.ktgram.types.media.LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendMessage(
        chatId, text, businessConnectionId, messageThreadId, parseMode, entities, linkPreviewOptions, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to get basic information about a file and prepare it for downloading. For the moment, bots can download files of up to 20MB in size. On success, a File object is returned. The file can then be downloaded via the link https://api.telegram.org/file/bot<token>/<file_path>, where <file_path> is taken from the response. It is guaranteed that the link will be valid for at least 1 hour. When the link expires, a new one can be requested by calling getFile again.
     */
    suspend fun getFile(fileId: String) = api.getFile(fileId)

    /**
     * Use this method to forward messages of any kind. Service messages and messages with protected content can't be forwarded. On success, the sent Message is returned.
     */
    suspend fun forwardMessage(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageId: Int,
    ) = api.forwardMessage(
        chatId, messageThreadId, fromChatId, disableNotification, protectContent, messageId
    )

    /**
     * Use this method to copy messages of any kind. Service messages, giveaway messages, giveaway winners messages, and invoice messages can't be copied. A quiz poll can be copied only if the value of the field correct_option_id is known to the bot. The method is analogous to the method forwardMessage, but the copied message doesn't have a link to the original message. Returns the MessageId of the sent message on success.
     */
    suspend fun copyMessage(
        chatId: Int,
        messageThreadId: Int? = null,
        fromChatId: Int,
        messageId: Int,
        caption: String? = null,
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null
    ) = api.copyMessage(
        chatId, messageThreadId, fromChatId, messageId, caption, parseMode, captionEntities, showCaptionAboveMedia, disableNotification, protectContent, replyParameters, replyMarkup
    )

    /**
     * Use this method to send photos. On success, the sent Message is returned.
     */
    suspend fun sendPhoto(
        chatId: Int,
        photo: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendPhoto(
        chatId, photo, businessConnectionId, messageThreadId, caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send photos by url or file id as string. On success, the sent Message is returned.
     */
    @Deprecated("Use sendPhoto with InputFile instead")
    suspend fun sendPhoto(
        chatId: Int,
        photo: String,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendPhoto(
        chatId, photo, businessConnectionId, messageThreadId, caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the music player. Your audio must be in the .MP3 or .M4A format. On success, the sent Message is returned. Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For sending voice messages, use the sendVoice method instead.
     */
    suspend fun sendAudio(
        chatId: Int,
        audio: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = default.parseMode,
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
    ) = api.sendAudio(
        chatId, audio, businessConnectionId, messageThreadId, caption, parseMode, captionEntities, duration, performer, title, thumbnail, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send general files. On success, the sent Message is returned. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     */
    suspend fun sendDocument(
        chatId: Int,
        document: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        thumbnail: InputFile? = null,
        caption: String? = null,
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendDocument(
        chatId, document, businessConnectionId, messageThreadId, thumbnail, caption, parseMode, captionEntities, disableContentTypeDetection, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send video files, Telegram clients support MPEG4 videos (other formats may be sent as Document). On success, the sent Message is returned. Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     */
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
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendVideo(
        chatId, video, businessConnectionId, messageThreadId, duration, width, height, thumbnail, caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, supportsStreaming, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On success, the sent Message is returned. Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     */
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
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendAnimation(
        chatId, animation, businessConnectionId, messageThreadId, duration, width, height, thumbnail, caption, parseMode, captionEntities, showCaptionAboveMedia, hasSpoiler, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. For this to work, your audio must be in an .OGG file encoded with OPUS, or in .MP3 format, or in .M4A format (other formats may be sent as Audio or Document). On success, the sent Message is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     */
    suspend fun sendVoice(
        chatId: Int,
        voice: InputFile,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        caption: String? = null,
        parseMode: String? = default.parseMode,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendVoice(
        chatId, voice, businessConnectionId, messageThreadId, caption, parseMode, captionEntities, duration, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * As of v.4.0, Telegram clients support rounded square MPEG4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     */
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
    ) = api.sendVideoNote(
        chatId, videoNote, businessConnectionId, messageThreadId, duration, length, thumbnail, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )
}