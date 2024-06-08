import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a parameter of the inline keyboard button used to automatically authorize a user. Serves as a great replacement for the Telegram Login Widget when the user is coming from Telegram. All the user needs to do is tap/click a button and confirm that they want to log in:
 *
 * TITLE
 * Telegram apps support these buttons as of version 5.7.
 *
 * Sample bot: @discussbot
 *
 * @param url An HTTPS URL to be opened with user authorization data added to the query string when the button is pressed. If the user refuses to provide authorization data, the original URL without information about the user will be opened. The data added is the same as described in [Receiving authorization data](https://core.telegram.org/bots/api#receiving-authorization-data).
 *
 * NOTE: You must always check the hash of the received data to verify the authentication and the integrity of the data as described in [Checking authorization](https://core.telegram.org/bots/api#checking-authorization).
 * @param forwardText *Optional.* New text of the button in forwarded messages.
 * @param botUsername *Optional.* Username of a bot, which will be used for user authorization. See [Setting up a bot](https://core.telegram.org/bots#setting-up-a-bot) for more details. If not specified, the current bot's username will be assumed. The url's domain must be the same as the domain linked with the bot. See [Linking your domain to the bot](https://core.telegram.org/bots/api#linking-your-domain-to-the-bot) for more details.
 * @param requestWriteAccess *Optional.* Pass True to request the permission for your bot to send messages to the user.
 */
@Serializable
class LoginUrl(
    @SerialName("url") val url: String,
    @SerialName("forward_text") val forwardText: String? = null,
    @SerialName("bot_username") val botUsername: String? = null,
    @SerialName("request_write_access") val requestWriteAccess: Boolean? = null,
) : TelegramObject()