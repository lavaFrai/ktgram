package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update

open class HandlerEnvironment(
    update: Update,
): UpdateEnvironment(update)