package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents an animated emoji that displays a random value.
 *
 * @param emoji Emoji on which the dice throw animation is based
 * @param value Value of the dice, 1-6 for “🎲”, “🎯” and “🎳” base emoji, 1-5 for “🏀” and “⚽” base emoji, 1-64 for “🎰” base emoji
 */
@Serializable
class Dice(
    @SerialName("emoji") val emoji: String,
    @SerialName("value") val value: Int,
) : TelegramObject()

enum class DiceEmoji(val emoji: String) {
    DICE("🎲"),
    DART("🎯"),
    BOWLING("🎳"),
    BASKETBALL("🏀"),
    FOOTBALL("⚽"),
    SLOT_MACHINE("🎰"),
}