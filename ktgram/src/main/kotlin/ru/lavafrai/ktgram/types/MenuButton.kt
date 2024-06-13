package ru.lavafrai.ktgram.types

import WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a menu button.
 *
 * @param type Type of the button, must be "default", "commands" or "web_app"
 * @param text *web_app* only. Text on the button
 * @param webApp *web_app* only. Description of the Web App that will be launched when the user presses the button. The Web App will be able to send an arbitrary message on behalf of the user using the method answerWebAppQuery.
 */
@Serializable
class MenuButton (
    @SerialName("type") val type: String,
    @SerialName("text") val text: String? = null,
    @SerialName("web_app") val webApp: WebAppInfo? = null,
): TelegramObject()