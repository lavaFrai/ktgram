package ru.lavafrai.ktgram.stateMachine.storage

interface BotStorage {
    suspend fun get(key: String): String?
    suspend fun set(key: String, value: String?)
    suspend fun clear(key: String)
}