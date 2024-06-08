package ru.lavafrai.ktgram.types

import kotlinx.serialization.Serializable

/**
 * This class represents the scope in which a bot command is applicable.
 * Currently, the following 7 scopes are supported:
 * - BotCommandScopeDefault
 * - BotCommandScopeAllPrivateChats
 * - BotCommandScopeAllGroupChats
 * - BotCommandScopeAllChatAdministrators
 * - BotCommandScopeChat
 * - BotCommandScopeChatAdministrators
 * - BotCommandScopeChatMember
 *
 * The list of commands for a particular user viewing the bot menu is determined by the first set of commands in the following order:
 * - Commands in the chat with the bot
 * - botCommandScopeChat + language_code
 * - botCommandScopeChat
 * - botCommandScopeAllPrivateChats + language_code
 * - botCommandScopeAllPrivateChats
 * - botCommandScopeDefault + language_code
 * - botCommandScopeDefault
 *
 * For group and supergroup chats, the order is as follows:
 * - botCommandScopeChatMember + language_code
 * - botCommandScopeChatMember
 * - botCommandScopeChatAdministrators + language_code (administrators only)
 * - botCommandScopeChatAdministrators (administrators only)
 * - botCommandScopeChat + language_code
 * - botCommandScopeChat
 * - botCommandScopeAllChatAdministrators + language_code (administrators only)
 * - botCommandScopeAllChatAdministrators (administrators only)
 * - botCommandScopeAllGroupChats + language_code
 * - botCommandScopeAllGroupChats
 * - botCommandScopeDefault + language_code
 * - botCommandScopeDefault
 *
 * @property type The type of the bot command scope.
 */
@Serializable
class BotCommandScope(
    val type: String,
): TelegramObject()