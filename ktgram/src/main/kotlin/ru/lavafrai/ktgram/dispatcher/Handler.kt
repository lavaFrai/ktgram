package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.environments.HandlerEnvironment

typealias Handler<reified T> = suspend T.() -> Unit