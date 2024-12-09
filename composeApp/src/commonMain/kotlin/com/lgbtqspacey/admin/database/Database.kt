package com.lgbtqspacey.admin.database

import com.lgbtqspacey.admin.database.api.TableSession
import com.lgbtqspacey.admin.database.api.TableSettings
import com.lgbtqspacey.admin.getPlatform

class Database {
    private val driver = getPlatform().databaseDriver
    private val sharedDatabase = SharedDatabase(driver)

    val session = TableSession(sharedDatabase)
    val settings = TableSettings(sharedDatabase)
}
