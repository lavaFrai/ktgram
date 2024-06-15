package ru.lavafrai.ktgram.types

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import ru.lavafrai.ktgram.client.BotContextMixin

@Serializable
open class TelegramObject(): BotContextMixin() {
    @OptIn(InternalSerializationApi::class)
    override fun toString(): String {
        val serializer = this.javaClass.kotlin.serializer()

        return tolerantJson.encodeToString(serializer, this)
    }

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        val tolerantJson = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        }
    }
}
