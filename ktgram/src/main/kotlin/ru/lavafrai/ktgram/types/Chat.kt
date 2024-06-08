package ru.lavafrai.ktgram.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class Chat(
    val id: Int,
    val type: String,
    val title: String? = null,
    val username: String? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("is_forum") val isForum: Boolean? = false,
) : TelegramObject() {

}
