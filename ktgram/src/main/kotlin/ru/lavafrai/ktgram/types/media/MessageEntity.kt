package ru.lavafrai.ktgram.types.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject
import ru.lavafrai.ktgram.types.User

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 *
 * @param type Type of the entity. Currently, can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD), “bot_command” (/start@jobs_bot), “url” (https://telegram.org), “email” (do-not-reply@telegram.org), “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), “underline” (underlined text), “strikethrough” (strikethrough text), “spoiler” (spoiler message), “blockquote” (block quotation), “expandable_blockquote” (collapsed-by-default block quotation), “code” (monowidth string), “pre” (monowidth block), “text_link” (for clickable text URLs), “text_mention” (for users without usernames), “custom_emoji” (for inline custom emoji stickers)
 * @param offset Offset in UTF-16 code units to the start of the entity
 * @param length Length of the entity in UTF-16 code units
 * @param url *Optional.* For “text_link” only, URL that will be opened after user taps on the text
 * @param user *Optional.* For “text_mention” only, the mentioned user
 * @param language *Optional.* For “pre” only, the programming language of the entity text
 * @param customEmojiId *Optional.* For “custom_emoji” only, unique identifier of the custom emoji. Use [getCustomEmojiStickers][ru.lavafrai.ktgram.client.Bot.getCustomEmojiStickers] to get full information about the sticker
 */
@Serializable
class MessageEntity(
    @SerialName("type") val type: String,
    @SerialName("offset") val offset: Int,
    @SerialName("length") val length: Int,
    @SerialName("url") val url: String? = null,
    @SerialName("user") val user: User? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("custom_emoji_id") val customEmojiId: String? = null,
) : TelegramObject()