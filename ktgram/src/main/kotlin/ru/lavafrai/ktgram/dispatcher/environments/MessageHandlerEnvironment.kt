package ktgram.dispatcher.environments

import ru.lavafrai.ktgram.dispatcher.scopes.UpdateHandlerEnvironment
import ru.lavafrai.ktgram.types.Message
import ru.lavafrai.ktgram.types.Update

class MessageHandlerEnvironment(update: Update, val message: Message) : UpdateHandlerEnvironment(update)