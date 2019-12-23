# Kos Sample using Gradle
A simple project written in Kotlin. It contains:
- A simple REST API
- An in-memory repository
- An exception handler

## Requirements
This project was configured to use JDK 11 but can be easily downgrade
to JDK 8 with a bit of tweak in `build.gradle.kts` file.

## Running the App
```shell
$ ./gradlew clean build
$ java -jar build/libs/kos-sample-kotlin-all.jar
```
