import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "me.moe"
version = "0.0.1"
description = "bruhbot"

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("com.github.ben-manes.versions") version "0.33.0"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("me.jakejmattson:DiscordKt:0.20.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}