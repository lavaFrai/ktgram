package ru.lavafrai.ktgram.stateMachine

import ru.lavafrai.ktgram.stateMachine.storage.keyFactory.KeyData

enum class Strategy {
    USER_IN_CHAT,
    CHAT,
    USER,
}

fun KeyData.applyStrategy(strategy: Strategy): KeyData {
    return copy(
        chatId = when (strategy) {
            Strategy.USER_IN_CHAT -> chatId
            Strategy.CHAT -> 0
            Strategy.USER -> 0
        },
        userId = when (strategy) {
            Strategy.USER_IN_CHAT -> userId
            Strategy.CHAT -> 0
            Strategy.USER -> userId
        },
    )
}