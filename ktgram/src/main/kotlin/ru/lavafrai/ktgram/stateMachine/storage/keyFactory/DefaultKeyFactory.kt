package ru.lavafrai.ktgram.stateMachine.storage.keyFactory

import ru.lavafrai.ktgram.stateMachine.Strategy
import ru.lavafrai.ktgram.stateMachine.applyStrategy

class DefaultKeyFactory(strategy: Strategy) : StorageKeyFactory(strategy) {
    override fun buildStateKey(keyData: KeyData): String {
        val actualData = keyData.applyStrategy(strategy)
        return "state:${actualData.botId}:${actualData.chatId}:${actualData.userId}"
    }

    override fun buildDataKey(keyData: KeyData): String {
        val actualData = keyData.applyStrategy(strategy)
        return "data:${actualData.botId}:${actualData.chatId}:${actualData.userId}"
    }
}