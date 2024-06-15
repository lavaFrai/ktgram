package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.stateMachine.getState
import ru.lavafrai.ktgram.types.MessageType
import ru.lavafrai.ktgram.types.UpdateType

fun Router.update(content: Router.() -> Unit) = addSubRouter(content) { true }

fun Router.update(vararg types: UpdateType, content: Router.() -> Unit) {
    addSubRouter(content) {
        update.type in types.toList()
    }
}

fun Router.message(content: Router.() -> Unit) = update(UpdateType.Message, content=content)

fun Router.message(vararg types: MessageType, content: Router.() -> Unit) {
    update(UpdateType.Message) {
        addSubRouter(content) {
            update.message!!.type in types.toList()
        }
    }
}

fun Router.text(content: Router.() -> Unit) = message(MessageType.Text, content=content)

fun Router.animation(content: Router.() -> Unit) = message(MessageType.Animation, content=content)

fun Router.audio(content: Router.() -> Unit) = message(MessageType.Audio, content=content)

fun Router.contact(content: Router.() -> Unit) = message(MessageType.Contact, content=content)

fun Router.dice(content: Router.() -> Unit) = message(MessageType.Dice, content=content)

fun Router.document(content: Router.() -> Unit) = message(MessageType.Document, content=content)

fun Router.location(content: Router.() -> Unit) = message(MessageType.Location, content=content)

fun Router.photo(content: Router.() -> Unit) = message(MessageType.Photo, content=content)

fun Router.sticker(content: Router.() -> Unit) = message(MessageType.Sticker, content=content)

fun Router.video(content: Router.() -> Unit) = message(MessageType.Video, content=content)

fun Router.videoNote(content: Router.() -> Unit) = message(MessageType.VideoNote, content=content)

fun Router.voice(content: Router.() -> Unit) = message(MessageType.Voice, content=content)

fun Router.venue(content: Router.() -> Unit) = message(MessageType.Venue, content=content)

fun Router.poll(content: Router.() -> Unit) = message(MessageType.Poll, content=content)

fun Router.newChatMembers(content: Router.() -> Unit) = message(MessageType.NewChatMembers, content=content)

fun Router.leftChatMember(content: Router.() -> Unit) = message(MessageType.LeftChatMember, content=content)

fun Router.state(state: String, content: Router.() -> Unit) {
    addSubRouter(content) {
        state == getState()
    }
}

fun Router.stateLess(content: Router.() -> Unit) {
    addSubRouter(content) {
        null == getState()
    }
}

fun Router.command(command: String, content: Router.() -> Unit) {
    text {
        addSubRouter(content) {
            update.message!!.text!!.startsWith("/$command")
        }
    }
}

fun Router.invoice(content: Router.() -> Unit) = message(MessageType.Invoice, content=content)

fun Router.payment(content: Router.() -> Unit) = message(MessageType.SuccessfulPayment, content=content)

fun Router.inlineQuery(content: Router.() -> Unit) = update(UpdateType.InlineQuery, content=content)

fun Router.callback(content: Router.() -> Unit)  = update(UpdateType.CallbackQuery, content=content)

fun Router.callback(startsWith: String? = null, notStartsWith: String? = null, content: Router.() -> Unit)  = update(UpdateType.CallbackQuery) {
    if (startsWith != null && notStartsWith != null) throw IllegalArgumentException("You can't use both startsWith and notStartsWith")

    addSubRouter(content) {
        when {
            startsWith != null -> update.callbackQuery!!.data!!.startsWith(startsWith)
            notStartsWith != null -> !update.callbackQuery!!.data!!.startsWith(notStartsWith)
            else -> true
        }
    }
}

fun Router.preCheckoutQuery(content: Router.() -> Unit) = update(UpdateType.PreCheckoutQuery, content=content)

fun Router.filter(predicate: Filter, content: Router.() -> Unit) {
    addSubRouter(content, predicate)
}