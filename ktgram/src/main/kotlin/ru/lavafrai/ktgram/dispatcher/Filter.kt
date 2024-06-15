package ru.lavafrai.ktgram.dispatcher

import ru.lavafrai.ktgram.dispatcher.environments.FilterEnvironment

typealias Filter = suspend FilterEnvironment.() -> Boolean?