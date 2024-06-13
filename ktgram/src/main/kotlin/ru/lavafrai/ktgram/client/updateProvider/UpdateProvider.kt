package ru.lavafrai.ktgram.client.updateProvider

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update
import java.util.concurrent.atomic.AtomicBoolean

class UpdateProvider(private val bot: Bot, private val timeout: Int = 5, val stopSignal: AtomicBoolean) {
    private var lastUpdate = 0

    fun getUpdatesFlow(): Flow<Update> {
        return flow {
            while (!stopSignal.get()) {
                val update = bot.api.getUpdates(offset = lastUpdate, limit = 1, timeout = 30).firstOrNull()
                update?.let {
                    lastUpdate = it.updateId + 1
                    emit(it)
                }
            }
        }
    }
}
