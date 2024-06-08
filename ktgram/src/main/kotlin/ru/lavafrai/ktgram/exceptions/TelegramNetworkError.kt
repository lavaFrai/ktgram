package ru.lavafrai.ktgram.exceptions

import ru.lavafrai.ktgram.types.TelegramObject

class TelegramNetworkError(e: Exception?=null, message: String?=null) : Exception(message, e)