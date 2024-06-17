package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.types.Update

class EnvironmentFactory<T: HandlerEnvironment>(private val clazz: Class<T>) {
    fun createEnvironment(update: Update): T {
        return clazz.getConstructor(Update::class.java).newInstance(update) as T
    }
}

val updateEnvironmentFactory = EnvironmentFactory(HandlerEnvironment::class.java)
val businessConnectionEnvironmentFactory = EnvironmentFactory(BusinessConnectionHandlerEnvironment::class.java)
val businessMessageEnvironmentFactory = EnvironmentFactory(BusinessMessageHandlerEnvironment::class.java)
val callbackQueryEnvironmentFactory = EnvironmentFactory(CallbackQueryHandlerEnvironment::class.java)
val channelPostEnvironmentFactory = EnvironmentFactory(ChannelPostHandlerEnvironment::class.java)
val chatBoostEnvironmentFactory = EnvironmentFactory(ChatBoostHandlerEnvironment::class.java)
val removedChatBoostEnvironmentFactory = EnvironmentFactory(RemovedChatBoostHandlerEnvironment::class.java)
val chatJoinRequestEnvironmentFactory = EnvironmentFactory(ChatJoinRequestHandlerEnvironment::class.java)
val chatMemberEnvironmentFactory = EnvironmentFactory(ChatMemberHandlerEnvironment::class.java)
val chosenInlineResultEnvironmentFactory = EnvironmentFactory(ChosenInlineResultHandlerEnvironment::class.java)
val deletedBusinessMessageEnvironmentFactory = EnvironmentFactory(DeletedBusinessMessagesHandlerEnvironment::class.java)
val editedBusinessMessageEnvironmentFactory = EnvironmentFactory(EditedBusinessMessageHandlerEnvironment::class.java)
val inlineQueryEnvironmentFactory = EnvironmentFactory(InlineQueryHandlerEnvironment::class.java)
val messageEnvironmentFactory = EnvironmentFactory(MessageHandlerEnvironment::class.java)
val messageEditEnvironmentFactory = EnvironmentFactory(MessageEditHandlerEnvironment::class.java)
val messageReactionEnvironmentFactory = EnvironmentFactory(MessageReactionHandlerEnvironment::class.java)
val myChatMemberEnvironmentFactory = EnvironmentFactory(MyChatMemberHandlerEnvironment::class.java)
val pollEnvironmentFactory = EnvironmentFactory(PollHandlerEnvironment::class.java)
val pollAnswerEnvironmentFactory = EnvironmentFactory(PollAnswerHandlerEnvironment::class.java)
val preCheckoutQueryEnvironmentFactory = EnvironmentFactory(PreCheckoutQueryHandlerEnvironment::class.java)
val shippingQueryEnvironmentFactory = EnvironmentFactory(ShippingQueryHandlerEnvironment::class.java)