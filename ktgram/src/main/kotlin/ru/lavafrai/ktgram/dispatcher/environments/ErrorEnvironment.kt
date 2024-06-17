package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class ErrorEnvironment(update: Update, val error: Exception): HandlerEnvironment(update)