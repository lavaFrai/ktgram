package ru.lavafrai.ktgram.types.inputfile

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class URLInputFile(val url: String, filename: String = "file"): InputFile(filename) {
    override suspend fun getBytes(): ByteArray { throw NotImplementedError() }
    override suspend fun getMultiPartBodyPart(name: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(name, url)
    }
}