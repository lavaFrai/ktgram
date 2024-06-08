package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.handlers.*
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment

fun Dispatcher.update(handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = UpdateHandler(handle, this)
    addHandler(handler)
}

fun Dispatcher.message(handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler({ update -> update.message != null }, handler=handle)
    addHandler(handler)
}

fun Dispatcher.filter(vararg filters: Filter, handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler(*filters, handler=handle)
    addHandler(handler)
}

fun Dispatcher.text(handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val handler = TextMessageHandler(handle, this)
    addHandler(handler)
}
