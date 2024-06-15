package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.types.Update

class Dispatcher(
    val bot: Bot,
    val strategy: DispatcherStrategy = DispatcherStrategy.HANDLE_ALL,
) {
    enum class DispatcherStrategy {
        HANDLE_ONE,
        HANDLE_ALL,
    }

    private val routers = mutableListOf<Router>()

    suspend fun handleUpdate(update: Update): Boolean {
        var handled = false

        routers.forEach { router ->
            if (router.canHandle(update)) {
                router.handleUpdate(update)
                handled = true

                if (strategy == DispatcherStrategy.HANDLE_ONE) return handled
            }
        }

        return handled
    }

    fun addRouter(router: Router) {
        routers.add(router)
    }
}