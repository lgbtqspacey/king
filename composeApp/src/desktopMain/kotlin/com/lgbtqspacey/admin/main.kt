package com.lgbtqspacey.admin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import java.awt.Dimension

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Admin Portal - LGBTQ+Spacey",
    ) {
        window.minimumSize = Dimension(600, 600)

        getPlatform().initSentry(getPlatform().name, getPlatform().version)
        Napier.base(DebugAntilog())

        App()
    }
}
