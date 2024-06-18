package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.environments.*
import ru.lavafrai.ktgram.dispatcher.middlewares.Middleware
import ru.lavafrai.ktgram.types.Update


class Router<T: HandlerEnvironment>(val dispatcher: Dispatcher) {
    constructor(dispatcher: Dispatcher, environmentFactory: EnvironmentFactory<T>, handler: Handler<T>): this(dispatcher) {
        this.environmentFactory = environmentFactory
        this.handler = handler
    }

    constructor(dispatcher: Dispatcher, environmentFactory: EnvironmentFactory<T>, filter: Filter, content: Router<T>.() -> Unit): this(dispatcher) {
        this.filter = filter
        this.environmentFactory = environmentFactory
        this.content()
    }

    private val subRoutes = mutableListOf<Router<*>>()
    private var handler: Handler<T>? = null
    private var filter: Filter? = null
    lateinit var environmentFactory: EnvironmentFactory<T>

    private val isEndpoint: Boolean
        get() = handler != null

    suspend fun canHandle(update: Update): Boolean {
        if (isEndpoint) return true

        val environment = FilterEnvironment(update)
        if (filter!!.invoke(environment) != true) return false
        return subRoutes.any { it.canHandle(update) }
    }

    suspend fun handleUpdate(update: Update): Boolean {
        if (isEndpoint) {
            val environment = getEnvironment(update)
            handler!!.invoke(environment)
            if (dispatcher.strategy == Dispatcher.DispatcherStrategy.HANDLE_ONE) return true
        }

        subRoutes.forEach {
            if (it.canHandle(update)) {
                val done = it.handleUpdate(update)
                if (done && dispatcher.strategy == Dispatcher.DispatcherStrategy.HANDLE_ONE) return true
            }
        }

        return false
    }

    fun addSubRouter(subRoute: Router<*>) {
        subRoutes.add(subRoute)
    }

    fun <E: HandlerEnvironment>addSubRouter(environmentFactory: EnvironmentFactory<E>, content: Router<E>.() -> Unit, filter: Filter) {
        val router = Router<E>(this.dispatcher, environmentFactory, filter, content)
        addSubRouter(router)
    }

    fun addSubRouter(content: Router<T>.() -> Unit, filter: Filter) {
        val router = Router<T>(this.dispatcher, environmentFactory, filter, content)
        addSubRouter(router)
    }

    private fun getEnvironment(update: Update): T {
        return environmentFactory.createEnvironment(update)
    }

    fun addMiddleware(middleware: Middleware) {
        dispatcher.addMiddleware(middleware)
    }

    fun addOuterMiddleware(middleware: Middleware) {
        dispatcher.addOuterMiddleware(middleware)
    }

    fun addErrorHandler(handler: Handler<ErrorEnvironment>) {
        dispatcher.addErrorHandler(handler)
    }
}

fun Dispatcher.routing(content: Router<HandlerEnvironment>.() -> Unit) {
    val f: Filter = { true }
    addRouter(Router(this, updateEnvironmentFactory, f, content))
}

fun <T: HandlerEnvironment>Router<T>.route(filter: Filter, content: Router<T>.() -> Unit) {
    val router = Router(this.dispatcher, this.environmentFactory, filter, content)
    addSubRouter(router)
}

fun <T: HandlerEnvironment>Router<T>.handle(handler: Handler<T>) {
    addSubRouter(Router<T>(this.dispatcher, this.environmentFactory, handler))
}

fun Router<*>.middleware(middleware: Middleware) {
    addMiddleware(middleware)
}

fun Router<*>.outerMiddleware(middleware: Middleware) {
    addOuterMiddleware(middleware)
}

fun Router<*>.errorHandler(handler: Handler<ErrorEnvironment>) {
    addErrorHandler(handler)
}