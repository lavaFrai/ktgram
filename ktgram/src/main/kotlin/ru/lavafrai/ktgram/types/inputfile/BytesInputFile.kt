package ru.lavafrai.ktgram.types.inputfile

import com.goncalossilva.resources.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BytesInputFile(val bytes: ByteArray, filename: String): InputFile() {
    override suspend fun getBytes(): ByteArray {
        return bytes
    }
}