package com.lgbtqspacey.king

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPlatform().initSentry(getPlatform().version)
        Napier.base(DebugAntilog())

        setContent {
            App()
        }
    }
}
