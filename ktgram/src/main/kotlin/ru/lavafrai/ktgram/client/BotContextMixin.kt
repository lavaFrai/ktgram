package ru.lavafrai.ktgram.client

import ru.lavafrai.ktgram.types.TelegramResult


open class BotContextMixin {
    @Transient
    lateinit var bot: Bot

}


fun <T: BotContextMixin>T.setContext(bot: Bot): T {
    this.bot = bot
    this::class.java.declaredFields.forEach {
        it.isAccessible = true
        it.get(this)?.let { obj ->
            if (obj is BotContextMixin) {
                obj.setContext(bot)
            }
        }
    }
    return this
}
