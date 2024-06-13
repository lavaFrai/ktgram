package ru.lavafrai.ktgram.types.inputfile

class ResourceInputFile(private val path: String, filename: String): InputFile(filename) {
    override suspend fun getBytes(): ByteArray {
        val resource = this.javaClass.classLoader.getResource(path)
        return resource?.readBytes() ?: throw RuntimeException("Resource not found path=$path")
    }
}