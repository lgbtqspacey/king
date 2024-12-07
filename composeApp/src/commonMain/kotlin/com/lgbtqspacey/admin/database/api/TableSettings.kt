package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.model.Settings
import com.lgbtqspacey.admin.helpers.errorHandler

class TableSettings(private val sharedDatabase: SharedDatabase) {
    suspend fun toggleDarkMode(): Boolean {
        try {
            sharedDatabase { db ->
                val isDarkMode = db.settingsQueries.getSettings().awaitAsOneOrNull()?.isDarkMode.toBoolean()
                db.settingsQueries.updateIsDarkTheme((!isDarkMode).toString())
            }
            return true
        } catch (exception: Exception) {
            errorHandler("TableSettings :: toggleDarkMode", exception)
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
            errorHandler("TableSettings :: toggleAutoUpdate", exception)
            return false
        }
    }

    suspend fun getSettings(): Settings {
        var settings = Settings()

        try {
            sharedDatabase { db ->
                val data = db.settingsQueries.getSettings().awaitAsOneOrNull()
                settings = Settings(
                    isAutoUpdate = data?.isAutoUpdate.toBoolean(),
                    isDarkMode = data?.isDarkMode.toBoolean(),
                )
            }
        } catch (exception: Exception) {
            errorHandler("TableSettings :: getSettings", exception)
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
            errorHandler("TableSettings :: deleteSettings", exception)
            return false
        }
    }
}
