package ru.lavafrai.ktgram.stateMachine


import ru.lavafrai.ktgram.dispatcher.environments.UpdateEnvironment

val UpdateEnvironment.stateMachine: StateMachine
    get() = update.bot.stateMachine

suspend fun UpdateEnvironment.setState(state: State) {
    this.stateMachine.setState(update, state)
}

suspend fun UpdateEnvironment.getState(): State? {
    return stateMachine.getState(update)
}

suspend fun UpdateEnvironment.clearState() {
    this.stateMachine.clearState(update)
}

suspend fun UpdateEnvironment.setData(data: Map<String, String>) {
    this.stateMachine.setData(update, data)
}

suspend fun UpdateEnvironment.getData(): MutableMap<String, String> {
    return this.stateMachine.getData(update)
}

suspend fun UpdateEnvironment.clearData() {
    this.stateMachine.clearData(update)
}

val UpdateEnvironment.data
    get() = object: StateMachineData {
        override suspend fun set(key: String, value: String) {
            val data = getData()
            data[key] = value
            setData(data)
        }

        override suspend fun get(key: String): String? {
            val data = getData()
            return data[key]
        }

        override suspend fun clear() {
            clearData()
        }
    }

interface StateMachineData {
    suspend fun set(key: String, value: String)
    suspend fun get(key: String): String?
    suspend fun clear()
}