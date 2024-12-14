# Introduction

Admin Portal for LGBTQ+Spacey.

## Requirements

- [Android Studio](https://developer.android.com/studio)
- [Java 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Gradle 8.8+](https://gradle.org/releases/)

## Getting started

1. Clone the repository `git clone https://github.com/lgbtqspacey/admin-portal.git`.
2. Open `admin-portal` in Android Studio.
3. Rename the file `composeApp/Secrets.example` to `composeApp/Secrets.kt` and update the values.
4. Sync the project.
5. Run the `:composeApp:run` Gradle task to start the desktop app.

## Structure

The project is structured as follows:
  - `commonMain` is for code that’s common for all targets.
  - `androidMain` and `DesktopMain` that contains specific code for each platform.

## Learn more
- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform).

### Check the backend [here](https://github.com/lgbtqspacey/admin-portal-server).
