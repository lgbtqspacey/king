package com.lgbtqspacey.admin.database

import java.util.prefs.Preferences

class Preferences {
    private val preferences: Preferences = Preferences.userNodeForPackage(this::class.java)

    fun insertData(key: String, value: String) = preferences.put(key, value)
    fun getData(key: String) = preferences[key, ""] ?: ""
    fun deleteData(key: String) = preferences.remove(key)
}
