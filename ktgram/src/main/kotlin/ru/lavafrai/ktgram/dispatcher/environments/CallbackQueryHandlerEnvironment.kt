package ru.lavafrai.ktgram.dispatcher.environments

import CallbackQuery
import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.Update

class CallbackQueryHandlerEnvironment(update: Update, val callbackQuery: CallbackQuery, val callbackData: String) : UpdateHandlerEnvironment(update)
