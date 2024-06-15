package ru.lavafrai.ktgram.types.inline.inputMessageContent

import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

@Serializable
sealed class InputMessageContent(): TelegramObject()