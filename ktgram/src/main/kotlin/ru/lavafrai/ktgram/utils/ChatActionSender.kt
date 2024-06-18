package ru.lavafrai.ktgram.utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.lavafrai.ktgram.dispatcher.environments.UpdateEnvironment
import ru.lavafrai.ktgram.types.ChatAction

@OptIn(DelicateCoroutinesApi::class)
suspend inline fun UpdateEnvironment.using(actionType: ChatAction, content: () -> Unit) {
    val coroutine = GlobalScope.launch {
        bot.sendChatAction(update.chat!!.id, actionType)
        delay(1000)
    }
    content()
    coroutine.cancel()
}