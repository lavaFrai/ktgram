package ru.lavafrai.ktgram.stateMachine.storage.keyFactory

import ru.lavafrai.ktgram.stateMachine.Strategy

abstract class StorageKeyFactory(val strategy: Strategy) {
    abstract fun buildStateKey(keyData: KeyData): String
    abstract fun buildDataKey(keyData: KeyData): String
}