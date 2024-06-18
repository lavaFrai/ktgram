package ru.lavafrai.ktgram.stateMachine

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.stateMachine.storage.BotStorage
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.DefaultKeyFactory
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.KeyData
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.StorageKeyFactory
import ru.lavafrai.ktgram.types.Update

class StateMachine(
    val bot: Bot,
    private val storage: BotStorage = bot.storage,
    private val strategy: Strategy = Strategy.USER_IN_CHAT,
    private val keyFactory: StorageKeyFactory = DefaultKeyFactory(strategy),
) {
    suspend fun getState(update: Update): State? {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        return storage.get(keyData)?.let { State.fromName(it) }
    }

    suspend fun setState(update: Update, state: State) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        storage.set(keyData, state.name)
    }

    suspend fun clearState(update: Update) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        storage.clear(keyData)
    }


    suspend fun getData(update: Update): MutableMap<String, String> {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildDataKey(KeyData(bot.id, user.id, chat.id))

        return storage.get(keyData)?.let { Json.decodeFromString<Map<String, String>>(it).toMutableMap() } ?: mutableMapOf()
    }

    suspend fun setData(update: Update, data: Map<String, String>) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildDataKey(KeyData(bot.id, user.id, chat.id))

        storage.set(keyData, Json.encodeToString(data))
    }

    suspend fun clearData(update: Update) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildDataKey(KeyData(bot.id, user.id, chat.id))

        storage.clear(keyData)
    }
}
