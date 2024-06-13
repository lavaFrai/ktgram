package ru.lavafrai.ktgram.types.replymarkup.replyKeyboard

import KeyboardButtonPollType
import KeyboardButtonRequestChat
import KeyboardButtonRequestUsers
import WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents one button of the reply keyboard. At most one of the optional fields must be used to specify type of the button. For simple text buttons, [String][kotlin.String] can be used instead of this object to specify the button text.
 *
 * @param text Text of the button. If none of the optional fields are used, it will be sent as a message when the button is pressed
 * @param requestUsers *Optional.* If specified, pressing the button will open a list of suitable users. Identifiers of selected users will be sent to the bot in a “users_shared” service message. Available in private chats only.
 * @param requestChat *Optional.* If specified, pressing the button will open a list of suitable chats. Tapping on a chat will send its identifier to the bot in a “chat_shared” service message. Available in private chats only.
 * @param requestContact *Optional.* If True, the user's phone number will be sent as a contact when the button is pressed. Available in private chats only.
 * @param requestLocation *Optional.* If True, the user's current location will be sent when the button is pressed. Available in private chats only.
 * @param requestPoll *Optional.* If specified, the user will be asked to create a poll and send it to the bot when the button is pressed. Available in private chats only.
 * @param webApp *Optional.* If specified, the described Web App will be launched when the button is pressed. The Web App will be able to send a “web_app_data” service message. Available in private chats only.
 *
 * Note: request_users and request_chat options will only work in Telegram versions released after 3 February, 2023. Older clients will display unsupported message.
 */
@Serializable
class KeyboardButton(
    @SerialName("text") val text: String,
    @SerialName("request_users") val requestUsers: KeyboardButtonRequestUsers? = null,
    @SerialName("request_chat") val requestChat: KeyboardButtonRequestChat? = null,
    @SerialName("request_contact") val requestContact: Boolean? = null,
    @SerialName("request_location") val requestLocation: Boolean? = null,
    @SerialName("request_poll") val requestPoll: KeyboardButtonPollType? = null,
    @SerialName("web_app") val webApp: WebAppInfo? = null,
) : TelegramObject()