package ru.lavafrai.ktgram.stateMachine

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.stateMachine.storage.BotStorage
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.DefaultKeyFactory
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.KeyData
import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.StorageKeyFactory
import ru.lavafrai.ktgram.types.Update

class StateMachine(
    val bot: Bot,
    val storage: BotStorage = bot.storage,
    private val strategy: Strategy = Strategy.USER_IN_CHAT,
    val keyFactory: StorageKeyFactory = DefaultKeyFactory(strategy),
) {
    suspend fun getState(update: Update): String? {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        return storage.get(keyData)
    }

    suspend fun setState(update: Update, state: String) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        storage.set(keyData, state)
    }

    suspend fun clearState(update: Update) {
        val user = update.from ?: throw IllegalStateException("It's impossible get to user from update=$update")
        val chat = update.chat ?: throw IllegalStateException("It's impossible get to chat from update=$update")

        val keyData = keyFactory.buildStateKey(KeyData(bot.id, user.id, chat.id))

        storage.clear(keyData)
    }
}
