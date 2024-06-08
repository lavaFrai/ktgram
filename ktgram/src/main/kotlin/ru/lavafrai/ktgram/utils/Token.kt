package ru.lavafrai.ktgram.utils


class TokenValidationException(message: String? = null) : Exception(message)


fun validateToken(token: String): Boolean {
    if (token.any { it.isWhitespace() }) {
        throw TokenValidationException("Token is invalid! It can't contains spaces.")
    }

    val (left, right) = token.split(":")
    if (left.isBlank() || (left.toLongOrNull() == null) || right.isBlank()) {
        throw TokenValidationException("Token is invalid!")
    }

    return true
}

fun extractBotId(token: String): Long {
    validateToken(token)
    val rawBotId = token.split(':').first()
    return rawBotId.toLong()
}
