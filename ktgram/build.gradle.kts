buildscript {
    repositories {
        mavenCentral()
    }
}

val ktorfitVersion = "2.0.0"

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "2.0.0"

    id("com.google.devtools.ksp") version "2.0.0-1.0.22"
    id("de.jensklingenberg.ktorfit") version "2.0.0"
}

group = "ru.lavafrai.ktgram"
version = "0.0.1"
val ktor_version: String by project

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")
    implementation("org.slf4j:slf4j-simple:2.0.13")
}

publishing {
    publications {
        create<MavenPublication>("Maven") {
            groupId = group as String
            version = version as String
            from(components["kotlin"])
        }
    }
}