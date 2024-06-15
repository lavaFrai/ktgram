package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.environments.FilterEnvironment
import ru.lavafrai.ktgram.dispatcher.environments.HandlerEnvironment
import ru.lavafrai.ktgram.types.Update

class Router(val dispatcher: Dispatcher) {
    constructor(dispatcher: Dispatcher, handler: Handler): this(dispatcher) {
        this.handler = handler
    }

    constructor(dispatcher: Dispatcher, filter: Filter, content: Router.() -> Unit): this(dispatcher) {
        this.filter = filter
        this.content()
    }

    private val subRoutes = mutableListOf<Router>()
    private var handler: Handler? = null
    private var filter: Filter? = null

    val isEndpoint: Boolean
        get() = handler != null

    suspend fun canHandle(update: Update): Boolean {
        if (isEndpoint) return true

        val environment = FilterEnvironment(update)
        if (filter!!.invoke(environment) != true) return false
        return subRoutes.any { it.canHandle(update) }
    }

    suspend fun handleUpdate(update: Update) {
        if (isEndpoint) {
            val environment = HandlerEnvironment(update)
            handler!!.invoke(environment)
            if (dispatcher.strategy == Dispatcher.DispatcherStrategy.HANDLE_ONE) return
        }

        subRoutes.forEach {
            if (it.canHandle(update)) {
                it.handleUpdate(update)
            }
        }
    }

    fun addSubRouter(subRoute: Router) {
        subRoutes.add(subRoute)
    }

    fun addSubRouter(content: Router.() -> Unit, filter: Filter) {
        val router = Router(this.dispatcher, filter, content)

        addSubRouter(router)
    }
}

fun Dispatcher.route(filter: Filter, content: Router.() -> Unit) {
    val router = Router(this, filter, content)

    addRouter(router)
}

fun Dispatcher.routing(content: Router.() -> Unit) {
    val f: Filter = { true }
    addRouter(Router(this, f, content))
}

fun Router.route(filter: Filter, content: Router.() -> Unit) {
    val router = Router(this.dispatcher, filter, content)

    addSubRouter(router)
}

fun Router.handle(handler: Handler) {
    addSubRouter(Router(this.dispatcher, handler))
}