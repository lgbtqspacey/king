This is a Kotlin Multiplatform project targeting Android and Desktop.

`/composeApp` is for code that will be shared across your Compose Multiplatform applications.

It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - `androidMain` and `DesktopMain` that contains specific code for each platform.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html), [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform).

You can open the desktop application by running the `:composeApp:run` Gradle task.
