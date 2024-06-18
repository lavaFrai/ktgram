package ru.lavafrai.ktgram.client

import BotCommand
import ReactionType
import ReplyParameters
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import org.slf4j.LoggerFactory
import ru.gildor.coroutines.okhttp.await
import ru.lavafrai.ktgram.client.updateProvider.UpdateProvider
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.middlewares.LoggingMiddleware
import ru.lavafrai.ktgram.stateMachine.StateMachine
import ru.lavafrai.ktgram.stateMachine.storage.BotStorage
import ru.lavafrai.ktgram.stateMachine.storage.MemoryStorage
import ru.lavafrai.ktgram.types.*
import ru.lavafrai.ktgram.types.inputfile.InputFile
import ru.lavafrai.ktgram.types.media.DiceEmoji
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.media.inputmedia.InputMedia
import ru.lavafrai.ktgram.types.poll.InputPollOption
import ru.lavafrai.ktgram.types.replymarkup.ReplyMarkup
import ru.lavafrai.ktgram.utils.extractBotId
import ru.lavafrai.ktgram.utils.validateToken
import java.net.Proxy
import java.util.concurrent.atomic.AtomicBoolean

class Bot (
    token: String,
    default: DefaultBotProperties? = null,
    val storage: BotStorage = MemoryStorage(),
    val proxy: Proxy? = null,
) {
    private val stopSignal = AtomicBoolean(false)
    private val _token: String
    private var default: DefaultBotProperties
    val api: TelegramApi
    private lateinit var me: User
    val logger = LoggerFactory.getLogger(Bot::class.java)
    private val updateFlow: Flow<Update>
    private val handlerScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    val dispatcher = Dispatcher(this)
    val stateMachine = StateMachine(this, storage)

    init {
        validateToken(token)

        this._token = token
        this.default = default ?: DefaultBotProperties()
        this.api = TelegramApi(this, proxy=proxy)
        this.updateFlow = UpdateProvider(this, this.default.timeout, stopSignal, logger).getUpdatesFlow()

        dispatcher.addOuterMiddleware(LoggingMiddleware(dispatcher, logger))
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
        dispatcher.handleUpdate(update)


        /*
        val handleEndTime = System.currentTimeMillis()
        if (handled) logger.info("Update id=${update.updateId} type=${update.type} is handled. Duration ${handleEndTime - handleStartTime} ms by bot id=${me.id}")
        else logger.info("Update id=${update.updateId} type=${update.type} is not handled by bot id=${me.id}")
         */
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

        val client = api.client
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
        chatId: Long,
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
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
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
        chatId: Long,
        messageThreadId: Int? = null,
        fromChatId: Long,
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
        chatId: Long,
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
     * Use this method to send audio files, if you want Telegram clients to display them in the music player. Your audio must be in the .MP3 or .M4A format. On success, the sent Message is returned. Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For sending voice messages, use the sendVoice method instead.
     */
    suspend fun sendAudio(
        chatId: Long,
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
        chatId: Long,
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
        chatId: Long,
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
        chatId: Long,
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
        chatId: Long,
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
    ) = api.sendVideoNote(
        chatId, videoNote, businessConnectionId, messageThreadId, duration, length, thumbnail, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup,
    )

    /**
     * Use this method to send a group of photos, videos, documents or audios as an album. Documents and audio files can be only grouped in an album with messages of the same type. On success, an array of Messages that were sent is returned.
     */
    suspend fun sendMediaGroup(
        chatId: Long,
        media: List<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
    ) = api.sendMediaGroup(
        chatId, media, businessConnectionId, messageThreadId, disableNotification, protectContent, messageEffectId, replyParameters,
    )

    /**
     * Use this method to send point on the map. On success, the sent Message is returned.
     */
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
    ) = api.sendLocation(chatId, latitude, longitude, businessConnectionId, messageThreadId, horizontalAccuracy, livePeriod, heading, proximityAlertRadius, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup)

    /**
     * Use this method to send information about a venue. On success, the sent Message is returned.
     */
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
    ) = api.sendVenue(chatId, latitude, longitude, title, address, businessConnectionId, messageThreadId, foursquareId, foursquareType, googlePlaceId, googlePlaceType, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup)

    /**
     * Use this method to send phone contacts. On success, the sent Message is returned.
     */
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
    ) = api.sendContact(chatId, phoneNumber, firstName, lastName, businessConnectionId, messageThreadId, vcard, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup)

    /**
     * Use this method to send a native poll. On success, the sent Message is returned.
     */
    suspend fun sendPoll(
        chatId: Long,
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
    ) = api.sendPoll(chatId, question, options, businessConnectionId, messageThreadId, questionParseMode, questionEntities, isAnonymous, type, allowsMultipleAnswers, correctOptionId, explanation, explanationParseMode, explanationEntities, openPeriod, closeDate, isClosed, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup)

    /**
     * Use this method to send an animated emoji that will display a random value. On success, the sent Message is returned.
     */
    suspend fun sendDice(
        chatId: Long,
        emoji: DiceEmoji? = null,
        businessConnectionId: String? = null,
        messageThreadId: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ) = api.sendDice(chatId, emoji?.emoji, businessConnectionId, messageThreadId, disableNotification, protectContent, messageEffectId, replyParameters, replyMarkup)

    /**
     * Use this method when you need to tell the user that something is happening on the bot's side. The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status). Returns True on success.
     */
    suspend fun sendChatAction(
        chatId: Long,
        action: String,
    ) = api.sendChatAction(chatId, action)

    /**
     * Use this method to change the chosen reactions on a message. Service messages can't be reacted to. Automatically forwarded messages from a channel to its discussion group have the same available reactions as messages in the channel. Returns True on success.
     */
    suspend fun setMessageReaction(
        chatId: Long,
        messageId: Int,
        reactions: List<ReactionType>,
    ) = api.setMessageReaction(chatId, messageId, reactions)

    suspend fun setCommands(commands: List<BotCommand>, scope: BotCommandScope? = null, languageCode: String? = null) = api.setMyCommands(commands, scope, languageCode)

    suspend fun deleteCommands(scope: BotCommandScope? = null, languageCode: String? = null) = api.deleteMyCommands(scope, languageCode)

    suspend fun getCommands(scope: BotCommandScope? = null, languageCode: String? = null) = api.getMyCommands(scope, languageCode)

    suspend fun setName(name: String, languageCode: String? = null) = api.setMyName(name, languageCode)

    suspend fun getName(languageCode: String? = null) = api.getMyName(languageCode)

    suspend fun setDescription(description: String, languageCode: String? = null) = api.setMyDescription(description, languageCode)

    suspend fun getDescription(languageCode: String? = null) = api.getMyDescription(languageCode)

    suspend fun setShortDescription(shortDescription: String, languageCode: String? = null) = api.setMyShortDescription(shortDescription, languageCode)

    suspend fun getShortDescription(languageCode: String? = null) = api.getMyShortDescription(languageCode)
}