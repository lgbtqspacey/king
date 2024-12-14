package com.lgbtqspacey.admin.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.lgbtqspacey.database.AppDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:admin.db")

        if (isDatabaseEmpty()) {
            AppDatabase.Schema.create(driver).await()
        } else {
            AppDatabase.Schema.create(driver)
        }
        return driver
    }

    /**
     * Checks if the database file is empty.
     * If `true`, then we should create the `SQL Schema` asynchronously so that it can create the tables.
     */
    private fun isDatabaseEmpty(): Boolean {
        val bashScript = """
            if [ -s "admin.db" ]; then
                exit 0
            else
                exit 1
            fi
        """.trimIndent()

        val process = ProcessBuilder("bash", "-c", bashScript).inheritIO().start().waitFor()
        return process == 1
    }
}
