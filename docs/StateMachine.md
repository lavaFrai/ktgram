# State Machine
> A finite-state machine (FSM) or finite-state automaton (FSA, plural: automata), finite automaton, or simply a state machine, is a mathematical model of computation.
>
> It is an abstract machine that can be in exactly one of a finite number of states at any given time. The FSM can change from one state to another in response to some inputs; the change from one state to another is called a transition.
>
> An FSM is defined by a list of its states, its initial state, and the inputs that trigger each transition.
>
> [Full article on Wikipedia](https://en.wikipedia.org/wiki/Finite-state_machine)

This bot library supports a state machine that allows you to create complex bots with a variety of states and transitions between them.
Also in routing DSL allowed state based routing.

## Usage example

First we need to declare states that will be needed:

```kotlin
sealed class StartStates {
    object WaitingName: State()
    object WaitingLikeBots: State()
    object WaitingLanguage: State()
}
```

After that we can start to write handlers for each state separately from the start of dialog.
Initially, you need to start the dialogue with the start command:

```kotlin
fun Router<*>.start() {
    command("start") {
        handle {
            data.clear() // Clear all data
            message.answer("Hello! What is your name?")
            setState(StartStates.WaitingName) // Transition to the next state
        }
    }
}
```

Then we can write handler to get users name:

```kotlin
fun Router<*>.name() {
    val likeBotsKeyboard = inlineKeyboard {  // Create inline keyboard to send 
        row {
            button("Yes!", "yes")
            button("No ", "no")
        }
    }

    text {
        state(StartStates.WaitingName) {
            handle {
                val name = message.text!!
                data.set("name", name)  // Store users name for further use 
                message.answer("Nice to meet you, $name!\nDo you like to write bots?", replyMarkup = likeBotsKeyboard)
            }
        }
    }
}
```

After that handling callback query:

```kotlin
fun Router<*>.likeBots() {
    state(StartStates.WaitingLikeBots) {
        callbackQuery("yes") {
            handle {
                query.message!!.answer("Awesome! I like to develop bots to!\nSo, what's your favorite programming language for that?")
                setState(StartStates.WaitingLanguage)

                query.answer()  // Do not forget to answer on callback query
            }
        }

        callbackQuery("no") {
            handle {
                query.message!!.answer("Not bad not terrible.\nSee you soon.")
                clearState()

                sendSummary(
                    update.chat!!,
                    data.get("name")!!,
                    false,
                    null,
                )

                query.answer()
            }
        }
    }
}
```

And the last state to get users favorite programming language:

```kotlin
fun Router<*>.language() {
    state(StartStates.WaitingLanguage) {
        text {
            handle {
                val language = message.text!!.lowercase()
                clearState()

                if (language == "kotlin") message.answer("Kotlin, you say? That's great choice! Good luck with that!")
                if (language == "python") message.answer("Oh. That's good choice but did you try Kotlin?")

                sendSummary(
                    message.chat,
                    data.get("name")!!,
                    true,
                    language
                )

                data.clear()  // Clear all data after dialog
            }
        }
    }
}
```

In final, we need to write a function to send summary of users answers:

```kotlin
suspend fun sendSummary(chat: Chat, name: String, likeBots: Boolean, language: String?) {
    val summary = buildString {
        append("Summary:\n")
        append("Name: $name\n")
        append("Like bots: ${if (likeBots) "Yes" else "No"}\n")
        append("Language: ${language ?: "Not specified"}")
    }

    chat.sendText(summary)
}
```

After that you can start your bot and test it.

Full example: [link](https://github.com/lavaFrai/ktgram/blob/master/examples/stateMachine/src/main/kotlin/ru/lavafrai/ktgram/examples/stateMachine/Main.kt)

## Important notes

 - Names of states serializes in low case. So be carefully because state names are case-insensitive. `State` and `state` are the same names.
 - Names of states serializes including parent object name. For example `StartStates.WaitingName` will be serialized to store as `startstates$waitingname`.

## Storages

By default, the state machine uses `MemoryStorage` to store states. But you can implement your own storage by implementing `StateStorage` interface.
Or use available implementations of storage [included](https://github.com/lavaFrai/ktgram/tree/master/ktgram/src/main/kotlin/ru/lavafrai/ktgram/stateMachine/storage) in framework.