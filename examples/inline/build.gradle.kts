plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {
    implementation(project(":ktgram"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}
