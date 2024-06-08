package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.handlers.*
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType

fun Dispatcher.update(handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = UpdateHandler(handle, this)
    addHandler(handler)
}

fun Dispatcher.update(vararg types: UpdateType, handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler({ update -> update.type in types }, handler=handle)
    addHandler(handler)
}

fun Dispatcher.message(handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    update(UpdateType.Message, handle=handle)
}

fun Dispatcher.message(vararg types: MessageType, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    messageFilter({ update -> update.message?.type in types.toList() }, handle=handle)
}

fun Dispatcher.filter(vararg filters: Filter, handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler(*filters, handler=handle)
    addHandler(handler)
}

fun Dispatcher.messageFilter(vararg filters: Filter, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val handler = MessageFilterHandler(*filters, handler=handle)
    addHandler(handler)
}

fun Dispatcher.text(handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val handler = TextMessageHandler(handle, this)
    addHandler(handler)
}