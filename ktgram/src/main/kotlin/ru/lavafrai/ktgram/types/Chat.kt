package ru.lavafrai.ktgram.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.inputfile.InputFile


@Serializable
class Chat(
    val id: Long,
    val type: String,
    val title: String? = null,
    val username: String? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("is_forum") val isForum: Boolean? = false,
) : TelegramObject() {
    suspend fun ban(userId: Long, untilDate: Int? = null, revokeMessages: Boolean? = null) {
        bot.api.banChatMember(id, userId, untilDate, revokeMessages)
    }

    suspend fun unban(userId: Long, onlyIfBanned: Boolean? = null) {
        bot.api.unbanChatMember(id, userId, onlyIfBanned)
    }

    suspend fun promote(
        userId: Long,
        isAnonymous: Boolean? = null,
        canManageChat: Boolean? = null,
        canDeleteMessages: Boolean? = null,
        canManageVideoChats: Boolean? = null,
        canRestrictMembers: Boolean? = null,
        canPromoteMembers: Boolean? = null,
        canChangeInfo: Boolean? = null,
        canInviteUsers: Boolean? = null,
        canPostStories: Boolean? = null,
        canEditStories: Boolean? = null,
        canDeleteStories: Boolean? = null,
        canPostMessages: Boolean? = null,
        canEditMessages: Boolean? = null,
        canPinMessages: Boolean? = null,
        canManageTopics: Boolean? = null,
    ) {
        bot.api.promoteChatMember(id, userId, isAnonymous, canManageChat, canDeleteMessages, canManageVideoChats, canRestrictMembers, canPromoteMembers, canChangeInfo, canInviteUsers, canPostStories, canEditStories, canDeleteStories, canPostMessages, canEditMessages, canPinMessages, canManageTopics)
    }

    suspend fun setPhoto(photo: InputFile) {
        bot.api.setChatPhoto(id, photo)
    }

    suspend fun deletePhoto() {
        bot.api.deleteChatPhoto(id)
    }

    suspend fun setTitle(title: String) {
        bot.api.setChatTitle(id, title)
    }

    suspend fun pin(messageId: Int) {
        bot.api.pinChatMessage(id, messageId)
    }

    suspend fun unpin(messageId: Int) {
        bot.api.unpinChatMessage(id, messageId)
    }

    suspend fun unpinAll() {
        bot.api.unpinAllChatMessages(id)
    }

    suspend fun leave() {
        bot.api.leaveChat(id)
    }

    suspend fun getFullInfo() {
        bot.api.getChat(id)
    }
}
