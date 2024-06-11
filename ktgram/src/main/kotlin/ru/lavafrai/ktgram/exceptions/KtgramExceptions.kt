package ru.lavafrai.ktgram.exceptions

object KtgramExceptions {
    class TelegramFileNotFoundException(fileId: String): Exception("Telegram file with id=$fileId not found")
}