package ru.lavafrai.ktgram.client.service.converters

import okhttp3.RequestBody
import retrofit2.Converter
import retrofit2.Retrofit
import ru.lavafrai.ktgram.types.TelegramList
import java.lang.reflect.Type


class TelegramListConverterFactory : Converter.Factory() {
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        if (type == TelegramList::class.java) {
            return Converter<String, RequestBody> { value -> RequestBody.create(null, value) }
        }
        return null
    }
}