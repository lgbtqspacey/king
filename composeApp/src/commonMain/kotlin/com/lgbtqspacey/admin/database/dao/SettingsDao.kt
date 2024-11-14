package com.lgbtqspacey.admin.database.dao

import com.lgbtqspacey.database.Settings

interface SettingsDao {
    suspend fun createSettings(settings: Settings)

    suspend fun getSettings(): Settings

    suspend fun deleteSettings()
}
