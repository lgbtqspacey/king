package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.database.Settings
import io.github.aakira.napier.Napier

class TableSettings(databaseDriver: DatabaseDriverFactory) {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    suspend fun createSettings(settings: Settings) {
        try {
            sharedDatabase { db ->
                db.settingsQueries.insertSettings(
                    isDarkTheme = settings.isDarkTheme,
                )
            }
        } catch (exception: Exception) {
            Napier.e("TableSettings :: createSettings", exception)
        }
    }

    suspend fun getSettings(): Settings {
        var settings = Settings( "true")

        try {
            sharedDatabase { db ->
                settings = db.settingsQueries.getSettings().awaitAsOne()
            }
        } catch (exception: Exception) {
            Napier.e("TableSettings :: getSettings", exception)
        }

        return settings
    }

    suspend fun deleteSettings() {
        try {
            sharedDatabase { db ->
                db.settingsQueries.deleteSettings()
            }
        } catch (exception: Exception) {
            Napier.e("TableSettings :: deleteSettings", exception)
        }
    }
}
