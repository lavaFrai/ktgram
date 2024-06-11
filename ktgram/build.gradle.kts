buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "2.0.0"
}

group = "ru.lavafrai.ktgram"
version = "0.0.1"
val ktor_version: String by project

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
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