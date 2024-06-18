package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.environments.*
import ru.lavafrai.ktgram.stateMachine.State
import ru.lavafrai.ktgram.stateMachine.getState
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType

fun Router<HandlerEnvironment>.update(content: Router<HandlerEnvironment>.() -> Unit) = addSubRouter(content) { true }

fun <T: HandlerEnvironment> Router<*>.update(vararg types: UpdateType, environmentFactory: EnvironmentFactory<T>, content: Router<T>.() -> Unit) {
    addSubRouter(environmentFactory, content) {
        update.type in types.toList()
    }
}

fun Router<*>.message(content: Router<MessageHandlerEnvironment>.() -> Unit) = update(UpdateType.Message, environmentFactory=messageEnvironmentFactory, content=content)

fun Router<*>.message(vararg types: MessageType, content: Router<MessageHandlerEnvironment>.() -> Unit) {
    update(UpdateType.Message, environmentFactory=messageEnvironmentFactory) {
        addSubRouter(content) {
            update.message!!.type in types.toList()
        }
    }
}

fun Router<*>.text(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Text, content=content)

fun Router<*>.animation(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Animation, content=content)

fun Router<*>.audio(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Audio, content=content)

fun Router<*>.contact(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Contact, content=content)

fun Router<*>.dice(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Dice, content=content)

fun Router<*>.document(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Document, content=content)

fun Router<*>.location(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Location, content=content)

fun Router<*>.photo(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Photo, content=content)

fun Router<*>.sticker(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Sticker, content=content)

fun Router<*>.video(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Video, content=content)

fun Router<*>.videoNote(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.VideoNote, content=content)

fun Router<*>.voice(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Voice, content=content)

fun Router<*>.venue(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Venue, content=content)

fun Router<*>.poll(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Poll, content=content)

fun Router<*>.invoice(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.Invoice, content=content)

fun Router<*>.payment(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.SuccessfulPayment, content=content)

fun Router<*>.newChatMembers(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.NewChatMembers, content=content)

fun Router<*>.leftChatMember(content: Router<MessageHandlerEnvironment>.() -> Unit) = message(MessageType.LeftChatMember, content=content)

fun <T: HandlerEnvironment> Router<T>.state(vararg state: State?, content: Router<T>.() -> Unit) {
    addSubRouter(content) {
        getState() in state
    }
}

fun <T: HandlerEnvironment> Router<T>.stateStartsWith(prefix: String, content: Router<T>.() -> Unit) {
    addSubRouter(content) {
        getState()?.name?.startsWith(prefix.lowercase()) ?: false
    }
}

fun <T: HandlerEnvironment> Router<T>.stateLess(content: Router<T>.() -> Unit) {
    addSubRouter(content) {
        null == getState()
    }
}

fun Router<*>.command(command: String, content: Router<MessageHandlerEnvironment>.() -> Unit) {
    text {
        addSubRouter(content) {
            update.message!!.text!!.startsWith("/$command")
        }
    }
}

fun Router<*>.inlineQuery(content: Router<InlineQueryHandlerEnvironment>.() -> Unit) = update(UpdateType.InlineQuery, environmentFactory=inlineQueryEnvironmentFactory, content=content)

fun Router<*>.callbackQuery(content: Router<CallbackQueryHandlerEnvironment>.() -> Unit)  = update(UpdateType.CallbackQuery, environmentFactory=callbackQueryEnvironmentFactory, content=content)

fun Router<*>.callbackQuery(data: String? = null, startsWith: String? = null, notStartsWith: String? = null, content: Router<CallbackQueryHandlerEnvironment>.() -> Unit)  = update(UpdateType.CallbackQuery, environmentFactory=callbackQueryEnvironmentFactory) {
    addSubRouter(content) {
        when {
            data != null -> update.callbackQuery!!.data == data
            startsWith != null -> update.callbackQuery!!.data!!.startsWith(startsWith)
            notStartsWith != null -> !update.callbackQuery!!.data!!.startsWith(notStartsWith)
            else -> true
        }
    }
}

fun Router<*>.preCheckoutQuery(content: Router<PreCheckoutQueryHandlerEnvironment>.() -> Unit) = update(UpdateType.PreCheckoutQuery, environmentFactory=preCheckoutQueryEnvironmentFactory, content=content)
