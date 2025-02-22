package com.lgbtqspacey.king

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.awaitApplication
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking
import java.awt.Dimension

fun main()  {
    runBlocking {
        awaitApplication {
            Window(
                onCloseRequest = ::exitApplication,
                title = "Kit Integrado de Gestão | LGBTQ+Spacey",
            ) {
                window.minimumSize = Dimension(600, 600)

                getPlatform().initSentry(getPlatform().name, getPlatform().version)
                Napier.base(DebugAntilog())

                App()
            }
        }
    }
}
