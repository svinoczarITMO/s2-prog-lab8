import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    application
}

group = "ru.itmo.se.prog.lab7"
version = "1.0-SNAPSHOT"

dependencies {
    val kotlinVersion = "1.8.10"
    val koinVersion = "3.2.2"
    implementation(project(":common"))
    testImplementation(kotlin("test"))
    implementation(kotlin("serialization", version = kotlinVersion))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("org.reflections:reflections:0.10.2")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation ("ch.qos.logback:logback-classic:1.2.9")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}