package com.lgbtqspacey.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    val driver = DatabaseDriverFactory(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPlatform().initSentry(getPlatform().name, getPlatform().version)
        Napier.base(DebugAntilog())

        setContent {
            App()
        }
    }
}
