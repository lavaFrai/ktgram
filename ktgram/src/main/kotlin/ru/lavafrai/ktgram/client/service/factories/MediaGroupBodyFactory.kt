package ru.lavafrai.ktgram.client.service.factories

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MultipartBody
import ru.lavafrai.ktgram.types.inputfile.FileIdInputFile
import ru.lavafrai.ktgram.types.inputfile.URLInputFile
import ru.lavafrai.ktgram.types.media.inputmedia.InputMedia

class MediaGroupBodyFactory(val json: Json) {
    suspend fun createMediaGroupBody(media: List<InputMedia>): List<MultipartBody.Part> {
        val mediaGroupBody = mutableListOf<MultipartBody.Part>()
        val processedMedia = mutableListOf<InputMedia>()

        media.forEach {
            when (it.mediaFile) {
                is URLInputFile, is FileIdInputFile -> {
                    processedMedia.add(it)
                }

                else -> {
                    val mediaName = it.media
                    val processedIt = it.copy(media = "attach://$mediaName")
                    processedMedia.add(processedIt)
                    mediaGroupBody.add(it.mediaFile!!.getMultiPartBodyPart(mediaName))
                }
            }
        }

        val mediaJson = json.encodeToString(processedMedia)
        mediaGroupBody.add(MultipartBody.Part.createFormData("media", mediaJson))
        return mediaGroupBody
    }
}