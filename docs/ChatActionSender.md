# Chat action sender

Bot supports to send chat actions to the chat automatically while processing the request. This feature is useful to show the user that the bot is working on the request.
To send chat action, you need to use the `using` function in the `UpdateEnvironment` scope.

For example:
```kotlin
fun Router<*>.handlers() {
    text {
        handle {
            using(ChatAction.TYPING) {
                delay(5000)
                message.reply("You said: ${update.message!!.text}")
            }
        }
    }
}
```

In this example, the bot will send the typing chat action to the chat for 5 seconds and then reply to the user with the message "You said: {text}".
You can use any chat action from the `ChatAction` enum in this.
