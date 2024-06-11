package ru.lavafrai.ktgram.client


class DefaultBotProperties(
    val parseMode: String?=null,
    val disableNotification: Boolean?=null,
    val protectContent: Boolean?=null,
    val allowSendingWithoutReply: Boolean?=null,
    val timeout: Int = 5
    /* TODO */
)