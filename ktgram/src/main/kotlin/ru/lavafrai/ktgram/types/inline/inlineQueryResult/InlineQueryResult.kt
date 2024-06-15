package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.lavafrai.ktgram.types.TelegramObject


@Serializable
open class InlineQueryResult(): TelegramObject()
