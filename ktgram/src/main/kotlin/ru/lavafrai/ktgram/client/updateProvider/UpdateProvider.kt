package ru.lavafrai.ktgram.client.updateProvider

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.slf4j.Logger
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

class UpdateProvider(
    private val bot: Bot,
    private val timeout: Int = 5,
    private val stopSignal: AtomicBoolean,
    private val logger: Logger
) {
    private var lastUpdate = 0

    fun getUpdatesFlow(): Flow<Update> {
        return flow {
            while (!stopSignal.get()) {
                val update = try {
                    bot.api.getUpdates(offset = lastUpdate, limit = 5, timeout = timeout)
                } catch (e: IOException) {
                    logger.error("Failed to get updates. Retrying in 5 s", e)
                    delay(5000)
                    continue
                }

                update.forEach {
                    lastUpdate = it.updateId + 1
                    emit(it)
                }
            }
        }
    }
}
