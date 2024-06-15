package ru.lavafrai.ktgram.stateMachine


import ru.lavafrai.ktgram.dispatcher.environments.UpdateEnvironment

val UpdateEnvironment.state: StateMachine
    get() = update.bot.stateMachine

suspend fun UpdateEnvironment.setState(state: String) {
    this.state.setState(update, state)
}

suspend fun UpdateEnvironment.getState(): String? {
    return state.getState(update)
}