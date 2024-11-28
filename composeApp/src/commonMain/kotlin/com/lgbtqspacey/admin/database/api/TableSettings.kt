package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.model.Settings
import io.github.aakira.napier.Napier
import io.sentry.kotlin.multiplatform.Sentry

class TableSettings(databaseDriver: DatabaseDriverFactory) {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    suspend fun toggleDarkMode(): Boolean {
        try {
            sharedDatabase { db ->
                val isDarkMode = db.settingsQueries.getSettings().awaitAsOneOrNull()?.isDarkMode.toBoolean()
                db.settingsQueries.updateIsDarkTheme((!isDarkMode).toString())
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
        var settings: Settings? = null

        try {
            sharedDatabase { db ->
                val data = db.settingsQueries.getSettings().awaitAsOneOrNull()
                settings = Settings(
                    isAutoUpdate = data?.isAutoUpdate.toBoolean(),
                    isDarkMode = data?.isDarkMode.toBoolean(),
                )
            }
        } catch (exception: Exception) {
            Napier.e("TableSettings :: getSettings", exception)
            Sentry.captureException(exception)
        }

        return settings ?: Settings( isDarkMode = true, isAutoUpdate = true)
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
