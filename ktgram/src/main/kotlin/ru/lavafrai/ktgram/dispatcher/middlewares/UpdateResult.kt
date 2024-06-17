package ru.lavafrai.ktgram.dispatcher.middlewares

import java.lang.Exception

/**
 * This class represents the result of the execution of a handler.
 *
 * @property hasException Indicates if the handler has thrown an exception.
 * @property exception The exception thrown by the handler, if any.
 * @property workTime The time taken by the handler to execute in milliseconds.
 */
data class UpdateResult(
    val hasException: Boolean,
    val exception: Exception? = null,
    val workTime: Long,
    val hasCanceled: Boolean,
    val cancellationCause: UpdateCancellationCause? = null,
)

enum class UpdateCancellationCause {
    CANCELED_BY_MIDDLEWARE,
    NO_HANDLER_FOUND,
}