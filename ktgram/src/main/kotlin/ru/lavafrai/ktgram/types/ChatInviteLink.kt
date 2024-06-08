import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * Represents an invite link for a chat.
 *
 * @param inviteLink The invite link. If the link was created by another chat administrator, then the second part of the link will be replaced with “…”.
 * @param creator Creator of the link
 * @param createsJoinRequest True, if users joining the chat via the link need to be approved by chat administrators
 * @param isPrimary True, if the link is primary
 * @param isRevoked True, if the link is revoked
 * @param name *Optional.* Invite link name
 * @param expireDate *Optional.* Point in time (Unix timestamp) when the link will expire or has been expired
 * @param memberLimit *Optional.* The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999
 * @param pendingJoinRequestCount *Optional.* Number of pending join requests created using this link
 */
@Serializable
class ChatInviteLink(
    @SerialName("invite_link") val inviteLink: String,
    @SerialName("creator") val creator: User,
    @SerialName("creates_join_request") val createsJoinRequest: Boolean,
    @SerialName("is_primary") val isPrimary: Boolean,
    @SerialName("is_revoked") val isRevoked: Boolean,
    @SerialName("name") val name: String? = null,
    @SerialName("expire_date") val expireDate: Int? = null,
    @SerialName("member_limit") val memberLimit: Int? = null,
    @SerialName("pending_join_request_count") val pendingJoinRequestCount: Int? = null,
) : TelegramObject()