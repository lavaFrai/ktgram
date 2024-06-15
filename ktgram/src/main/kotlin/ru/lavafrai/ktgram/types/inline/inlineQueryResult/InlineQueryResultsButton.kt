package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a button to be shown above inline query results. You must use exactly one of the optional fields.
 *
 * @param text Label text on the button
 * @param webApp Description of the Web App that will be launched when the user presses the button. The Web App will be able to switch back to the inline mode using the method switchInlineQuery inside the Web App.
 * @param startParameter Deep-linking parameter for the /start message sent to the bot when a user presses the button. 1-64 characters, only A-Z, a-z, 0-9, _ and - are allowed.
 */
@Serializable
sealed class InlineQueryResultsButton(
    @SerialName("text") val text: String,
    @SerialName("web_app") val webApp: WebAppInfo? = null,
    @SerialName("start_parameter") val startParameter: String? = null,
): TelegramObject() {
}