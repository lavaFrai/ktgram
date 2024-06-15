package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup

/**
 * Represents a Game.
 *
 * @param type Type of the result, must be game
 * @param id Unique identifier for this result, 1-64 bytes
 * @param gameShortName Short name of the game
 * @param replyMarkup Inline keyboard attached to the message
 */
@Serializable
class InlineQueryResultGame(
    @SerialName("type") val type: String = "game",
    @SerialName("id") val id: String,
    @SerialName("game_short_name") val gameShortName: String,
    @SerialName("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
): ru.lavafrai.ktgram.types.inline.inlineQueryResult.InlineQueryResult() {
}