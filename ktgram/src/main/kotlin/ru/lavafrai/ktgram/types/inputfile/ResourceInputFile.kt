package ru.lavafrai.ktgram.types.inputfile

import com.goncalossilva.resources.Resource

class ResourceInputFile(private val path: String, filename: String): InputFile() {
    override suspend fun getBytes(): ByteArray {
        val resource = this.javaClass.classLoader.getResource(path)
        return resource?.readBytes() ?: throw RuntimeException("Resource not found path=$path")
    }
}