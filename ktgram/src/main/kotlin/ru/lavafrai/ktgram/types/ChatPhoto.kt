import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object represents a chat photo.
 *
 * @param smallFileId File identifier of small (160x160) chat photo. This file_id can be used only for photo download and only for as long as the photo is not changed.
 * @param smallFileUniqueId Unique file identifier of small (160x160) chat photo, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
 * @param bigFileId File identifier of big (640x640) chat photo. This file_id can be used only for photo download and only for as long as the photo is not changed.
 * @param bigFileUniqueId Unique file identifier of big (640x640) chat photo, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
 */
@Serializable
class ChatPhoto(
    @SerialName("small_file_id") val smallFileId: String,
    @SerialName("small_file_unique_id") val smallFileUniqueId: String,
    @SerialName("big_file_id") val bigFileId: String,
    @SerialName("big_file_unique_id") val bigFileUniqueId: String,
) : TelegramObject()