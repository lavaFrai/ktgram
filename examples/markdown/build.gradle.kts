plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {
    implementation(project(":ktgram"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}
