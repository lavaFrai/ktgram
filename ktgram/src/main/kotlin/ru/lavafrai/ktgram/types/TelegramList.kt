package ru.lavafrai.ktgram.types

class TelegramList<T>(val list: List<T>) {
    override fun toString(): String {
        return list.toString()
    }
}

fun <T> List<T>.toTelegramList(): TelegramList<T> {
    return TelegramList(this)
}