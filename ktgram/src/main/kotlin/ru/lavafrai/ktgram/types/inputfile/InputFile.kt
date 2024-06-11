package ru.lavafrai.ktgram.types.inputfile

import kotlinx.serialization.Serializable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.lavafrai.ktgram.types.TelegramObject

/**
 *   This object represents the contents of a file to be uploaded. Must be posted using multipart/form-data in the usual way that files are uploaded via the browser.
 */
@Serializable
abstract class InputFile(private val filename: String? = null): TelegramObject() {
    abstract suspend fun getBytes(): ByteArray
    suspend fun getMultiPartBodyPart(name: String): MultipartBody.Part {
        val requestBody = RequestBody.create(null, getBytes())
        return MultipartBody.Part.createFormData(name, filename ?: name, requestBody)
    }

    companion object {
        fun fromBytes(bytes: ByteArray, filename: String = "file") = BytesInputFile(bytes, filename)
        fun fromResource(path: String, filename: String = "file") = ResourceInputFile(path, filename)
    }
}