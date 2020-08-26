import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.github.johnrengelman.shadow") version "6.0.0"
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    application
}
group = "dev.maxtrewartha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/ktor")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
    }
}
dependencies {
    testImplementation(kotlin("test-junit"))
    // For the http server etc
    implementation("io.javalin:javalin:3.9.1")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.0-alpha1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")

    // Stuff for kaml (yaml)
    implementation(kotlin("stdlib", "stdlib-jdk8")) // or
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC") // JVM dependency
    implementation("com.charleskorn.kaml:kaml:0.19.0")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "dev.maxtrewartha.devibe.MainKt"
}