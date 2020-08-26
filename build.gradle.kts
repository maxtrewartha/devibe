import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.johnrengelman.shadow") version "6.0.0"
    kotlin("jvm") version "1.4.0"
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
    implementation("io.javalin:javalin:3.9.1")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.0-alpha1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "dev.maxtrewartha.devibe.MainKt"
}