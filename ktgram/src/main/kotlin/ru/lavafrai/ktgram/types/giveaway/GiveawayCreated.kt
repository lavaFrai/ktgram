package ru.lavafrai.ktgram.types.giveaway

import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a service message about the creation of a scheduled giveaway. Currently holds no information.
 */
@Serializable
class GiveawayCreated : TelegramObject()