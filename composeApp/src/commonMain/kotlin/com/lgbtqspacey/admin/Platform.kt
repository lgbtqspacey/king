package com.lgbtqspacey.admin

import com.lgbtqspacey.admin.database.DatabaseDriverFactory

abstract class  Platform {
    open val name: String = ""
    open val version: String = "1.0.0"
    abstract val databaseDriver: DatabaseDriverFactory
}

expect fun getPlatform(): Platform
