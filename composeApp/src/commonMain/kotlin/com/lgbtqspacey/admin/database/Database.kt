package com.lgbtqspacey.admin.database

import com.lgbtqspacey.admin.database.api.TableSession
import com.lgbtqspacey.admin.database.api.TableSettings
import com.lgbtqspacey.admin.getPlatform

class Database {
    private val driver = getPlatform().databaseDriver

    val settings = TableSettings(driver)
    val session = TableSession(driver)
}
