package com.lgbtqspacey.admin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Admin Portal - LGBTQ+Spacey",
    ) {
        Napier.base(DebugAntilog())
        App()
    }
}
