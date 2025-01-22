package com.lgbtqspacey.admin.database.dao

import com.lgbtqspacey.admin.database.Preferences
import com.lgbtqspacey.admin.database.model.Settings
import com.lgbtqspacey.admin.helpers.errorHandler

class SettingsDao {
    private val db = Preferences()

    private companion object {
        const val SETTINGS_IS_DARK_MODE = "settings_is_dark_mode"
        const val SETTINGS_IS_AUTO_UPDATE = "settings_is_auto_update"
    }

    fun getSettings(): Settings {
        try {
            val isDarkMode = db.getData(SETTINGS_IS_DARK_MODE).toBoolean()
            val isAutoUpdate = db.getData(SETTINGS_IS_AUTO_UPDATE).toBoolean()

            return Settings(isDarkMode, isAutoUpdate)
        } catch (exception: Exception) {
            errorHandler("SettingsDao :: getSettings", exception)
            return Settings()
        }
    }

    fun toggleDarkMode(): Boolean {
        try {
            val currentIsDark = db.getData(SETTINGS_IS_DARK_MODE)

            if (currentIsDark.toBoolean()) {
                db.insertData(SETTINGS_IS_DARK_MODE, "false")
            } else {
                db.insertData(SETTINGS_IS_DARK_MODE, "true")
            }
            return true
        } catch (exception: Exception) {
            errorHandler("SettingsDao :: toggleDarkMode", exception)
            return false
        }
    }

    fun toggleAutoUpdate(): Boolean {
        try {
            val currentIsAutoUpdate = db.getData(SETTINGS_IS_AUTO_UPDATE)

            if (currentIsAutoUpdate.toBoolean()) {
                db.insertData(SETTINGS_IS_AUTO_UPDATE, "false")
            } else {
                db.insertData(SETTINGS_IS_AUTO_UPDATE, "true")
            }
            return true
        } catch (exception: Exception) {
            errorHandler("SettingsDao :: toggleAutoUpdate", exception)
            return false
        }
    }
}
