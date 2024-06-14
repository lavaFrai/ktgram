package ru.lavafrai.ktgram.types

class TelegramList<T>(val list: List<T>) {
    override fun toString(): String {
        return list.toString()
    }
}

inline fun <reified T> List<T>.toTelegramList(): TelegramList<T> {
    if (T::class.java == String::class.java) return TelegramList(this.map { "\"$it\"" }) as TelegramList<T>

    return TelegramList(this)
}