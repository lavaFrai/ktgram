package ru.lavafrai.ktgram.client

import ReplyParameters
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory
import ru.lavafrai.ktgram.client.eventProvider.UpdateProvider
import ru.lavafrai.ktgram.client.service.TelegramApiService
import ru.lavafrai.ktgram.client.service.productionTelegramApiService
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.types.*
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
}