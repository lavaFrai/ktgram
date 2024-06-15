package ru.lavafrai.ktgram.stateMachine

import ru.lavafrai.ktgram.dispatcher.environments.UpdateHandlerEnvironment

val UpdateHandlerEnvironment.state: StateMachine
    get() = update.bot.stateMachine

suspend fun UpdateHandlerEnvironment.getState() {
    state.getState(update)
}

suspend fun UpdateHandlerEnvironment.setState(state: String) {
    this.state.setState(update, state)
}