import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.Chat
import ru.lavafrai.ktgram.types.ChatMember
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents changes in the status of a chat member.
 *
 * @param chat Chat the user belongs to
 * @param from Performer of the action, which resulted in the change
 * @param date Date the change was done in Unix time
 * @param oldChatMember Previous information about the chat member
 * @param newChatMember New information about the chat member
 * @param inviteLink *Optional.* Chat invite link, which was used by the user to join the chat; for joining by invite link events only.
 * @param viaJoinRequest *Optional.* True, if the user joined the chat after sending a direct join request without using an invite link and being approved by an administrator
 * @param viaChatFolderInviteLink *Optional.* True, if the user joined the chat via a chat folder invite link
 */
@Serializable
class ChatMemberUpdated(
    @SerialName("chat") val chat: Chat,
    @SerialName("from") val from: User,
    @SerialName("date") val date: Int,
    @SerialName("old_chat_member") val oldChatMember: ChatMember,
    @SerialName("new_chat_member") val newChatMember: ChatMember,
    @SerialName("invite_link") val inviteLink: ChatInviteLink? = null,
    @SerialName("via_join_request") val viaJoinRequest: Boolean? = null,
    @SerialName("via_chat_folder_invite_link") val viaChatFolderInviteLink: Boolean? = null,
) : TelegramObject()