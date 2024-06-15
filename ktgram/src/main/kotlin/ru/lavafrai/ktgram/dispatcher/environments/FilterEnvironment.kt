package ru.lavafrai.ktgram.dispatcher.environments

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update

class FilterEnvironment(
    update: Update,
    bot: Bot = update.bot,
): UpdateEnvironment(update, bot)