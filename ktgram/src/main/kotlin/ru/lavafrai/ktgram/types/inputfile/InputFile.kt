package ru.lavafrai.ktgram.types.inputfile

import kotlinx.serialization.Serializable
import okhttp3.MultipartBody
import ru.lavafrai.ktgram.types.TelegramObject

/**
 *   This object represents the contents of a file to be uploaded. Must be posted using multipart/form-data in the usual way that files are uploaded via the browser.
 */
@Serializable
abstract class InputFile(
    val filename: String,
    val chunk_size: ByteArray,
): TelegramObject() {
    abstract suspend fun getMultiPartBodyPart(): MultipartBody.Part
}