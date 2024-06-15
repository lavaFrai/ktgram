plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ktgram"
include(":ktgram")
include(":examples:echo")
include(":examples:filters")
include(":examples:test")
include(":examples:files")
include(":examples:mediaGroup")
include(":examples:inlineKeyboard")
include(":examples:payments")
include(":examples:inline")
include(":examples:stateMachine")