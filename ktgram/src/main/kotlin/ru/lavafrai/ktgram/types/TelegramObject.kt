package ru.lavafrai.ktgram.types

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import ru.lavafrai.ktgram.client.BotContextMixin

@Serializable
open class TelegramObject(): BotContextMixin() {
    @OptIn(InternalSerializationApi::class)
    override fun toString(): String {
        val serializer = this.javaClass.kotlin.serializer()

        return Json.encodeToString(serializer, this)
    }
}
