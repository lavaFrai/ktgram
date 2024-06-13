package ru.lavafrai.ktgram.types.inputfile

class BytesInputFile(val bytes: ByteArray, filename: String): InputFile() {
    override suspend fun getBytes(): ByteArray {
        return bytes
    }
}