package ru.lavafrai.ktgram.types.replymarkup

import ru.lavafrai.ktgram.types.replymarkup.InlineKeyboardButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 *
 * @param inlineKeyboard Array of button rows, each represented by an Array of [ru.lavafrai.ktgram.types.replymarkup.InlineKeyboardButton][ru.lavafrai.ktgram.types.ru.lavafrai.ktgram.types.replymarkup.InlineKeyboardButton] objects
 */
@Serializable
class InlineKeyboardMarkup(
    @SerialName("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyMarkup()