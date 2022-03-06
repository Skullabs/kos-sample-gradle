import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    id("io.vertx.vertx-plugin") version "1.3.0"
}

// Versions
val version_kos = "0.8.0"
val version_logback = "1.2.3"
val version_jackson = "2.11.3"

// Configuration
val targetJvm = "11"
val launcherClass = "kos.core.Launcher"

repositories {
    mavenCentral()
    mavenLocal()
}

kapt {
    correctErrorTypes = true
    showProcessorTimings = true
}

dependencies {
    // kotlin conf
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${version_jackson}")
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    // kos
    implementation(platform("io.skullabs.kos:kos-bom:${version_kos}"))
    implementation("io.skullabs.kos:kos-core")
    implementation("io.skullabs.kos:kos-injector")

    // Annotation processors in Gradle requires kapt plugin
    compileOnly("io.skullabs.kos:kos-annotations")
    kapt("io.skullabs.kos:kos-annotations:${version_kos}")

    // logging configuration
    implementation("ch.qos.logback:logback-classic:${version_logback}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = targetJvm
}

vertx {
    launcher = launcherClass
}
