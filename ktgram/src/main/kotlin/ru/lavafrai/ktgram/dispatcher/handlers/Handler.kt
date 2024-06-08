package ru.lavafrai.ktgram.dispatcher.handlers

import ru.lavafrai.ktgram.types.Update

interface Handler {
    suspend fun predict(update: Update): Boolean
    suspend fun handle(update: Update)
}