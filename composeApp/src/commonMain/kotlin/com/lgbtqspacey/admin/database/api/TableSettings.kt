package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.model.Settings
import io.github.aakira.napier.Napier
import io.sentry.kotlin.multiplatform.Sentry

class TableSettings(private val sharedDatabase: SharedDatabase) {
    suspend fun toggleDarkMode(): Boolean {
        try {
            sharedDatabase { db ->
                val current = db.settingsQueries.getSettings().awaitAsOneOrNull()?.isDarkMode.toBoolean()
                println("current: $current")

                db.settingsQueries.updateIsDarkTheme((!current).toString())
                println("update")

                val up = db.settingsQueries.getSettings().awaitAsOneOrNull()
                println("updated: $up")
            }

            return true
        } catch (exception: Exception) {
            Napier.e("TableSettings :: toggleDarkMode", exception)
            Sentry.captureException(exception)
            return false
        }
    }

    suspend fun toggleAutoUpdate(): Boolean {
        try {
            sharedDatabase { db ->
                val isAutoUpdate = db.settingsQueries.getSettings().awaitAsOneOrNull()?.isAutoUpdate.toBoolean()
                db.settingsQueries.updateIsAutoUpdate((!isAutoUpdate).toString())
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSettings :: toggleAutoUpdate", exception)
            Sentry.captureException(exception)
            return false
        }
    }

    suspend fun getSettings(): Settings {
        var settings = Settings()

        try {
            sharedDatabase { db ->
                val data = db.settingsQueries.getSettings().awaitAsOneOrNull()
                println("get: $data")
                settings = Settings(
                    isAutoUpdate = data?.isAutoUpdate.toBoolean(),
                    isDarkMode = data?.isDarkMode.toBoolean(),
                )
            }
        } catch (exception: Exception) {
            Napier.e("TableSettings :: getSettings", exception)
            Sentry.captureException(exception)
        }

        return settings
    }

    suspend fun deleteSettings(): Boolean {
        try {
            sharedDatabase { db ->
                db.settingsQueries.deleteSettings()
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSettings :: deleteSettings", exception)
            Sentry.captureException(exception)
            return false
        }
    }
}
