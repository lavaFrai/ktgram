package ru.lavafrai.ktgram.stateMachine.storage

class MemoryStorage: BotStorage {
    private val storage: MutableMap<String, String> = mutableMapOf()

    override suspend fun get(key: String): String? {
        return storage[key]
    }

    override suspend fun set(key: String, value: String?) {
        if (value == null) clear(key)
        else storage[key] = value
    }

    override suspend fun clear(key: String) {
        storage.remove(key)
    }
}