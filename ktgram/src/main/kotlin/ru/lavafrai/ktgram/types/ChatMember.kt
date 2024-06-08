package ru.lavafrai.ktgram.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * This class represents a member of a chat in Telegram.
 *
 * @property user The user object contains information about the user.
 * @property status The member's status in the chat.
 * @property customTitle Custom title for this user.
 * @property isAnonymous True, if the user's presence in the chat is hidden.
 * @property untilDate Restricted and kicked only. Date when restrictions will be lifted for this user.
 * @property canBeEdited Administrators only. True, if the bot is allowed to edit administrator privileges of that user.
 * @property canPostMessages Administrators only. True, if the administrator can post in the channel; channels only.
 * @property canEditMessages Administrators only. True, if the administrator can edit messages of other users and can pin messages; channels only.
 * @property canDeleteMessages Administrators only. True, if the administrator can delete messages of other users.
 * @property canRestrictMembers Administrators only. True, if the administrator can restrict, ban or unban chat members.
 * @property canPromoteMembers Administrators only. True, if the administrator can add new administrators with a subset of their own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user).
 * @property canChangeInfo Administrators and bots only. True, if the user is allowed to change the chat title, photo and other settings.
 * @property canInviteUsers Administrators and bots only. True, if the user is allowed to invite new users to the chat.
 * @property canPinMessages Administrators and bots only. True, if the user is allowed to pin messages; groups and supergroups only.
 * @property isMember Restricted only. True, if the user is a member of the chat at the moment of the request.
 * @property canSendMessages Restricted only. True, if the user is allowed to send text messages, contacts, locations and venues.
 * @property canSendMediaMessages Restricted only. True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes.
 * @property canSendPolls Restricted only. True, if the user is allowed to send polls.
 * @property canSendOtherMessages Restricted only. True, if the user is allowed to send animations, games, stickers and use inline bots.
 * @property canAddWebPagePreviews Restricted only. True, if the user is allowed to add web page previews to their messages.
 */
@Serializable
class ChatMember(
    @SerialName("user") val user: User,
    @SerialName("status") val status: String,
    @SerialName("custom_title") val customTitle: String? = null,
    @SerialName("is_anonymous") val isAnonymous: Boolean? = null,
    @SerialName("until_date") val untilDate: Int? = null,
    @SerialName("can_be_edited") val canBeEdited: Boolean? = null,
    @SerialName("can_post_messages") val canPostMessages: Boolean? = null,
    @SerialName("can_edit_messages") val canEditMessages: Boolean? = null,
    @SerialName("can_delete_messages") val canDeleteMessages: Boolean? = null,
    @SerialName("can_restrict_members") val canRestrictMembers: Boolean? = null,
    @SerialName("can_promote_members") val canPromoteMembers: Boolean? = null,
    @SerialName("can_change_info") val canChangeInfo: Boolean? = null,
    @SerialName("can_invite_users") val canInviteUsers: Boolean? = null,
    @SerialName("can_pin_messages") val canPinMessages: Boolean? = null,
    @SerialName("is_member") val isMember: Boolean? = null,
    @SerialName("can_send_messages") val canSendMessages: Boolean? = null,
    @SerialName("can_send_media_messages") val canSendMediaMessages: Boolean? = null,
    @SerialName("can_send_polls") val canSendPolls: Boolean? = null,
    @SerialName("can_send_other_messages") val canSendOtherMessages: Boolean? = null,
    @SerialName("can_add_web_page_previews") val canAddWebPagePreviews: Boolean? = null,
) : TelegramObject()