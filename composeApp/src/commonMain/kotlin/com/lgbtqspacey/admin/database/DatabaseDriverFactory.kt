package com.lgbtqspacey.admin.database

import app.cash.sqldelight.db.SqlDriver
import com.lgbtqspacey.database.AppDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseDriverFactory {
    suspend fun createDriver(): SqlDriver
}

class SharedDatabase(private val databaseDriverFactory: DatabaseDriverFactory) {
    private var database: AppDatabase? = null

    private suspend fun initDatabase() {
        database.takeIf { it != null } ?: run {
            database = AppDatabase(databaseDriverFactory.createDriver())
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return database.takeIf { it != null }?.let {
            block(it)
        } ?: throw IllegalStateException("Database is not initialized")
    }
}
