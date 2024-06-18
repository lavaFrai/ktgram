# ktgram

---

**ktgram** is a modern and fully asynchronous framework for Telegram Bot's implementing the Telegram Bot API with kotlin.

---

## Warning
Now this library is in the early stages of development and is not ready for production use. 

## Features
- Asynchronous with kotlin [coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- Implements [Telegram Bot API 7.5](https://core.telegram.org/bots/api)
- And many more in the near future!

## Simple 
You can run a simple echo bot with just a few lines of code.

```kotlin
import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.dispatcher.Dispatcher
import ru.lavafrai.ktgram.dispatcher.dsl.text


fun main() {
    val bot = Bot("<Your token>")

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        text {
            handle {
                update.message!!.reply("You said: ${message.text}")
            }
        }
    }

    bot.runPolling()
}
```

## Installation
Add the following to your `build.gradle.kts` file:

```kotlin
repositories {
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.lavafrai:ktgram:$ktgramVersion")
}
```

## Roadmap
- [x] Basic functionality
- [x] State machine
- [x] Middlewares
- [ ] Integrated i18n
- [ ] Documentation
- [x] Keyboards DSL
- [x] Payments support

## Thanks
- [aiogram](https://github.com/aiogram/aiogram) for idea to implement the same functional framework for kotlin and some architecture solutions.
- [kotlin-telegram-bot](https://github.com/kotlin-telegram-bot/kotlin-telegram-bot) for some realization ideas.
