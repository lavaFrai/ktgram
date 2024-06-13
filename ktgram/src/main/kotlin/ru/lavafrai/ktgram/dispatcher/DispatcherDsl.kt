package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.handlers.*
import ktgram.dispatcher.environments.MessageHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.environments.CallbackQueryHandlerEnvironment
import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType

fun Dispatcher.update(handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = UpdateHandler(handle, this)
    addHandler(handler)
}

fun Dispatcher.update(vararg types: UpdateType, handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler({ update.type in types }, handler=handle)
    addHandler(handler)
}

fun Dispatcher.message(handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val handler = MessageHandler(handler = handle, dispatcher = this)
    addHandler(handler)
}

fun Dispatcher.message(vararg types: MessageType, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    messageFilter({ message.type in types.toList() }, handle=handle)
}

fun Dispatcher.filter(vararg filters: Filter, handle: suspend UpdateHandlerEnvironment.() -> Unit) {
    val handler = FilterHandler(*filters, handler=handle)
    addHandler(handler)
}

fun Dispatcher.messageFilter(vararg filters: MessageFilter, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val handler = MessageHandler(*filters, handler=handle, dispatcher = this)
    addHandler(handler)
}

fun Dispatcher.text(vararg filters: MessageFilter, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val filtersList = filters.toMutableList()
    filtersList.add { message.type == MessageType.Text }

    val handler = MessageHandler(*filtersList.toTypedArray(), handler=handle, dispatcher = this)
    addHandler(handler)
}

fun Dispatcher.photo(vararg filters: MessageFilter, handle: suspend MessageHandlerEnvironment.() -> Unit) {
    val filtersList = filters.toMutableList()
    filtersList.add { message.type == MessageType.Photo }

    val handler = MessageHandler(*filtersList.toTypedArray(), handler=handle, dispatcher = this)
    addHandler(handler)
}

fun Dispatcher.callback(vararg filters: CallbackQueryFilter, handle: suspend CallbackQueryHandlerEnvironment.() -> Unit) {
    val filtersList = filters.toMutableList()

    val handler = CallbackQueryHandler(*filtersList.toTypedArray(), handler=handle, dispatcher = this)
    addHandler(handler)
}

