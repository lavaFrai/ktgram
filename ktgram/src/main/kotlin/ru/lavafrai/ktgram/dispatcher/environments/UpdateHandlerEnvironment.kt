package ru.lavafrai.ktgram.dispatcher.scopes

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update

open class UpdateHandlerEnvironment(
    val update: Update,
    val bot: Bot = update.bot,
) {

}