import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    id("io.vertx.vertx-plugin") version "1.0.1"
}

// Versions
val version_kos = "0.1.0-SNAPSHOT"
val version_injector = "1.2.0"
val version_logback = "1.2.3"
val version_jackson = "2.9.+"

// Configuration
val targetJvm = "11"
val launcherClass = "kos.core.Launcher"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

kapt {
    correctErrorTypes = true
    showProcessorTimings = true
}

dependencies {
    implementation(platform("io.skullabs.kos:kos-bom:${version_kos}"))

    implementation(kotlin("stdlib"))
    implementation("io.skullabs.kos:kos-core")
    implementation("io.skullabs.kos:kos-annotations")
    implementation("io.skullabs.kos:kos-logging-slf4j")
    implementation("ch.qos.logback:logback-classic:${version_logback}")
    implementation("io.skullabs.kos:kos-injector")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${version_jackson}")

    // Annotation processors in Gradle requires kapt plugin
    compileOnly("io.skullabs.kos:kos-annotations")
    compileOnly("io.skullabs.injector:injector-processor")
    kapt("io.skullabs.kos:kos-annotations:${version_kos}")
    kapt("io.skullabs.injector:injector-processor:${version_injector}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = targetJvm
}

vertx {
    launcher = launcherClass
}