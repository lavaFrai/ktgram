package ru.lavafrai.ktgram.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class TelegramList<T>(@Transient val list: List<T> = emptyList()) {
    override fun toString(): String {
        return list.toString()
    }
}

inline fun <reified T> List<T>.toTelegramList(): TelegramList<T> {
    if (T::class.java == String::class.java) return TelegramList(this.map { "\"$it\"" }) as TelegramList<T>

    return TelegramList(this)
}