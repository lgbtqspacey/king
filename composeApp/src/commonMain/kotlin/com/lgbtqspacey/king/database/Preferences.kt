package com.lgbtqspacey.king.database

import java.util.prefs.Preferences
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Preferences {
    private val preferences: Preferences = Preferences.userNodeForPackage(this::class.java)

    /**
     * Save data to preference using Base64 encoding
     */
    fun insertData(key: String, value: String) {
        preferences.put(key, encode(value))
    }

    /**
     * Get data from preference using Base64 decoding
     */
    fun getData(key: String): String {
        preferences.let {
            return decode(it[key, ""])
        }
    }

    /**
     * Delete data from preference
     */
    fun deleteData(key: String) {
        preferences.remove(key)
    }


    @OptIn(ExperimentalEncodingApi::class)
    private fun encode(data: String): String {
        return Base64.Default.encode(data.encodeToByteArray())
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun decode(data: String): String {
        return Base64.Default.decode(data).decodeToString()
    }
}
