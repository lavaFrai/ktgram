import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * Describes a Web App.
 *
 * @param url An HTTPS URL of a Web App to be opened with additional data as specified in [Initializing Web Apps](https://core.telegram.org/bots/webapps)
 */
@Serializable
class WebAppInfo(
    @SerialName("url") val url: String,
) : TelegramObject()