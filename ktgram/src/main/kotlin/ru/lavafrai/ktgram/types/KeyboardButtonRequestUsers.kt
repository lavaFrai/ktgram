import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object defines the criteria used to request suitable users. Information about the selected users will be shared with the bot when the corresponding button is pressed. More about requesting users »
 *
 * @param requestId Signed 32-bit identifier of the request that will be received back in the [UsersShared][ru.lavafrai.ktgram.types.UsersShared] object. Must be unique within the message
 * @param userIsBot *Optional.* Pass True to request bots, pass False to request regular users. If not specified, no additional restrictions are applied.
 * @param userIsPremium *Optional.* Pass True to request premium users, pass False to request non-premium users. If not specified, no additional restrictions are applied.
 * @param maxQuantity *Optional.* The maximum number of users to be selected; 1-10. Defaults to 1.
 * @param requestName *Optional.* Pass True to request the users' first and last names
 * @param requestUsername *Optional.* Pass True to request the users' usernames
 * @param requestPhoto *Optional.* Pass True to request the users' photos
 */
@Serializable
class KeyboardButtonRequestUsers(
    @SerialName("request_id") val requestId: Int,
    @SerialName("user_is_bot") val userIsBot: Boolean? = null,
    @SerialName("user_is_premium") val userIsPremium: Boolean? = null,
    @SerialName("max_quantity") val maxQuantity: Int? = null,
    @SerialName("request_name") val requestName: Boolean? = null,
    @SerialName("request_username") val requestUsername: Boolean? = null,
    @SerialName("request_photo") val requestPhoto: Boolean? = null,
) : TelegramObject()