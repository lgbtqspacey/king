package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsList
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.dao.SettingsDao
import com.lgbtqspacey.database.Settings

class SettingsDaoImpl(databaseDriver: DatabaseDriverFactory) : SettingsDao {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    override suspend fun createSettings(settings: Settings) {
        sharedDatabase { db ->
            db.settingsQueries.insertSettings(
                id = settings.id,
                isDarkTheme = settings.isDarkTheme,
            )
        }
    }

    override suspend fun getSettings(): List<Settings> {
        var settingsList = listOf<Settings>()

        sharedDatabase { db ->
            settingsList = db.settingsQueries.getSettings().awaitAsList()
        }

        return settingsList
    }

    override suspend fun deleteSettings() {
        sharedDatabase { db ->
            db.settingsQueries.deleteSettings()
        }
    }
}
