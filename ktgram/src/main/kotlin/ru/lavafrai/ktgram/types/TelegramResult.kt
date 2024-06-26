package ru.lavafrai.ktgram.types

import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.client.setContext

@Serializable
data class TelegramResult<T> (
    val ok: Boolean,
    val result: T? = null
)


fun <O: TelegramObject, T: TelegramResult<O>> T.getResult(bot: Bot): O {
    this.result!!.setContext(bot)
    return this.result
}

fun <O: List<TelegramObject>, T: TelegramResult<O>> T.getResult(bot: Bot): O {
    this.result!!.map { it.setContext(bot) }
    return this.result
}

fun <O: Int, T: TelegramResult<O>> T.getResult(): O {
    return this.result!!
}

fun <O: List<Int>, T: TelegramResult<O>> T.getResult(): O {
    return this.result!!
}

fun <O: Boolean, T: TelegramResult<O>> T.getResult(): O {
    return this.result!!
}

fun <O: String, T: TelegramResult<O>> T.getResult(): O {
    return this.result!!
}