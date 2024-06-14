package ru.lavafrai.ktgram.types

import CallbackQuery
import ChatBoostRemoved
import ChatBoostUpdated
import ChatJoinRequest
import ChatMemberUpdated
import MessageReactionCountUpdated
import MessageReactionUpdated
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.business.BusinessConnection
import ru.lavafrai.ktgram.types.business.BusinessMessagesDeleted
import ru.lavafrai.ktgram.types.payments.PreCheckoutQuery
import ru.lavafrai.ktgram.types.poll.Poll
import ru.lavafrai.ktgram.types.poll.PollAnswer

/**
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.
 *
 * @param updateId The update's unique identifier. Update identifiers start from a certain positive number and increase sequentially. This identifier becomes especially handy if you're using webhooks, since it allows you to ignore repeated updates or to restore the correct update sequence, should they get out of order. If there are no new updates for at least a week, then identifier of the next update will be chosen randomly instead of sequentially.
 * @param message Optional. New incoming message of any kind - text, photo, sticker, etc.
 * @param editedMessage Optional. New version of a message that is known to the bot and was edited. This update may at times be triggered by changes to message fields that are either unavailable or not actively used by your bot.
 * @param channelPost Optional. New incoming channel post of any kind - text, photo, sticker, etc.
 * @param editedChannelPost Optional. New version of a channel post that is known to the bot and was edited. This update may at times be triggered by changes to message fields that are either unavailable or not actively used by your bot.
 * @param businessConnection Optional. The bot was connected to or disconnected from a business account, or a user edited an existing connection with the bot
 * @param businessMessage Optional. New message from a connected business account
 * @param editedBusinessMessage Optional. New version of a message from a connected business account
 * @param deletedBusinessMessages Optional. Messages were deleted from a connected business account
 * @param messageReaction Optional. A reaction to a message was changed by a user. The bot must be an administrator in the chat and must explicitly specify "message_reaction" in the list of allowed_updates to receive these updates. The update isn't received for reactions set by bots.
 * @param messageReactionCount Optional. Reactions to a message with anonymous reactions were changed. The bot must be an administrator in the chat and must explicitly specify "message_reaction_count" in the list of allowed_updates to receive these updates. The updates are grouped and can be sent with delay up to a few minutes.
 * @param inlineQuery Optional. New incoming inline query
 * @param chosenInlineResult Optional. The result of an inline query that was chosen by a user and sent to their chat partner. Please see our documentation on the feedback collecting for details on how to enable these updates for your bot.
 * @param callbackQuery Optional. New incoming callback query
 * @param shippingQuery Optional. New incoming shipping query. Only for invoices with flexible price
 * @param preCheckoutQuery Optional. New incoming pre-checkout query. Contains full information about checkout
 * @param poll Optional. New poll state. Bots receive only updates about manually stopped polls and polls, which are sent by the bot
 * @param pollAnswer Optional. A user changed their answer in a non-anonymous poll. Bots receive new votes only in polls that were sent by the bot itself.
 * @param myChatMember Optional. The bot's chat member status was updated in a chat. For private chats, this update is received only when the bot is blocked or unblocked by the user.
 * @param chatMember Optional. A chat member's status was updated in a chat. The bot must be an administrator in the chat and must explicitly specify "chat_member" in the list of allowed_updates to receive these updates.
 * @param chatJoinRequest Optional. A request to join the chat has been sent. The bot must have the can_invite_users administrator right in the chat to receive these updates.
 * @param chatBoost Optional. A chat boost was added or changed. The bot must be an administrator in the chat to receive these updates.
 * @param removedChatBoost Optional. A boost was removed from a chat. The bot must be an administrator in the chat to receive these updates.
 */
@Serializable
class Update(
    @SerialName("update_id") val updateId: Int,
    @SerialName("message") val message: Message? = null,
    @SerialName("edited_message") val editedMessage: Message? = null,
    @SerialName("channel_post") val channelPost: Message? = null,
    @SerialName("edited_channel_post") val editedChannelPost: Message? = null,
    @SerialName("business_connection") val businessConnection: BusinessConnection? = null,
    @SerialName("business_message") val businessMessage: Message? = null,
    @SerialName("edited_business_message") val editedBusinessMessage: Message? = null,
    @SerialName("deleted_business_messages") val deletedBusinessMessages: BusinessMessagesDeleted? = null,
    @SerialName("message_reaction") val messageReaction: MessageReactionUpdated? = null,
    @SerialName("message_reaction_count") val messageReactionCount: MessageReactionCountUpdated? = null,
    // @SerialName("inline_query") val inlineQuery: InlineQuery? = null,
    // @SerialName("chosen_inline_result") val chosenInlineResult: ChosenInlineResult? = null,
    @SerialName("callback_query") val callbackQuery: CallbackQuery? = null,
    // @SerialName("shipping_query") val shippingQuery: ShippingQuery? = null,
    @SerialName("pre_checkout_query") val preCheckoutQuery: PreCheckoutQuery? = null,
    @SerialName("poll") val poll: Poll? = null,
    @SerialName("poll_answer") val pollAnswer: PollAnswer? = null,
    @SerialName("my_chat_member") val myChatMember: ChatMemberUpdated? = null,
    @SerialName("chat_member") val chatMember: ChatMemberUpdated? = null,
    @SerialName("chat_join_request") val chatJoinRequest: ChatJoinRequest? = null,
    @SerialName("chat_boost") val chatBoost: ChatBoostUpdated? = null,
    @SerialName("removed_chat_boost") val removedChatBoost: ChatBoostRemoved? = null,
) : TelegramObject() {
    val type: UpdateType
        get() {
            return when {
                message != null -> UpdateType.Message
                editedMessage != null -> UpdateType.EditedMessage
                channelPost != null -> UpdateType.ChannelPost
                editedChannelPost != null -> UpdateType.EditedChannelPost
                callbackQuery != null -> UpdateType.CallbackQuery
                pollAnswer != null -> UpdateType.PollAnswer
                myChatMember != null -> UpdateType.MyChatMember
                chatMember != null -> UpdateType.ChatMember
                chatJoinRequest != null -> UpdateType.ChatJoinRequest
                messageReaction != null -> UpdateType.MessageReaction
                chatBoost != null -> UpdateType.ChatBoost
                removedChatBoost != null -> UpdateType.RemovedChatBoost
                poll != null -> UpdateType.Poll
                preCheckoutQuery != null -> UpdateType.PreCheckoutQuery

                else -> UpdateType.Unknown
            }
        }
}