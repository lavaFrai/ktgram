import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ktgram.types.stickers.Sticker
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.giveaway.Giveaway
import ru.lavafrai.ktgram.types.giveaway.GiveawayWinners
import ru.lavafrai.ktgram.types.media.*
import ru.lavafrai.ktgram.types.poll.Poll

/**
 * This object contains information about a message that is being replied to, which may come from another chat or forum topic.
 *
 * @param origin Origin of the message replied to by the given message
 * @param chat *Optional.* Chat the original message belongs to. Available only if the chat is a supergroup or a channel.
 * @param messageId *Optional.* Unique message identifier inside the original chat. Available only if the original chat is a supergroup or a channel.
 * @param linkPreviewOptions *Optional.* Options used for link preview generation for the original message, if it is a text message
 * @param animation *Optional.* Message is an animation, information about the animation
 * @param audio *Optional.* Message is an audio file, information about the file
 * @param document *Optional.* Message is a general file, information about the file
 * @param photo *Optional.* Message is a photo, available sizes of the photo
 * @param sticker *Optional.* Message is a sticker, information about the sticker
 * @param story *Optional.* Message is a forwarded story
 * @param video *Optional.* Message is a video, information about the video
 * @param videoNote *Optional.* Message is a video note, information about the video message
 * @param voice *Optional.* Message is a voice message, information about the file
 * @param hasMediaSpoiler *Optional.* True, if the message media is covered by a spoiler animation
 * @param contact *Optional.* Message is a shared contact, information about the contact
 * @param dice *Optional.* Message is a dice with random value
 * @param game *Optional.* Message is a game, information about the game. More about games »
 * @param giveaway *Optional.* Message is a scheduled giveaway, information about the giveaway
 * @param giveawayWinners *Optional.* A giveaway with public winners was completed
 * @param invoice *Optional.* Message is an invoice for a payment, information about the invoice. More about payments »
 * @param location *Optional.* Message is a shared location, information about the location
 * @param poll *Optional.* Message is a native poll, information about the poll
 * @param venue *Optional.* Message is a venue, information about the venue
 */
@Serializable
class ExternalReplyInfo(
    // @SerialName("origin") val origin: MessageOrigin, /* TODO */
    @SerialName("chat") val chat: Chat? = null,
    @SerialName("message_id") val messageId: Int? = null,
    @SerialName("link_preview_options") val linkPreviewOptions: LinkPreviewOptions? = null,
    @SerialName("animation") val animation: Animation? = null,
    @SerialName("audio") val audio: Audio? = null,
    @SerialName("document") val document: Document? = null,
    @SerialName("photo") val photo: List<PhotoSize>? = null,
    @SerialName("sticker") val sticker: Sticker? = null,
    @SerialName("story") val story: Story? = null,
    @SerialName("video") val video: Video? = null,
    @SerialName("video_note") val videoNote: VideoNote? = null,
    @SerialName("voice") val voice: Voice? = null,
    @SerialName("has_media_spoiler") val hasMediaSpoiler: Boolean? = null,
    @SerialName("contact") val contact: Contact? = null,
    @SerialName("dice") val dice: Dice? = null,
    // @SerialName("game") val game: Game? = null,
    @SerialName("giveaway") val giveaway: Giveaway? = null,
    @SerialName("giveaway_winners") val giveawayWinners: GiveawayWinners? = null,
    // @SerialName("invoice") val invoice: Invoice? = null,
    @SerialName("location") val location: Location? = null,
    @SerialName("poll") val poll: Poll? = null,
    @SerialName("venue") val venue: Venue? = null,
) : TelegramObject()