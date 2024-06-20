package ru.lavafrai.ktgram.client

import okio.Timeout
import ru.lavafrai.ktgram.types.ParseMode

class DefaultBotProperties(
    val parseMode: ParseMode? = ParseMode.NONE,
    val disableNotification: Boolean?=null,
    val protectContent: Boolean?=null,
    val allowSendingWithoutReply: Boolean?=null,
    val timeout: Int = 5
    /* TODO */
)