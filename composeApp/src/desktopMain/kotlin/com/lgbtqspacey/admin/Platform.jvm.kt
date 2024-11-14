package com.lgbtqspacey.admin

import com.lgbtqspacey.admin.database.DatabaseDriverFactory

class JVMPlatform: Platform() {
    override val name: String = "Desktop: Java ${System.getProperty("java.version")}"
    override val databaseDriver: DatabaseDriverFactory = DatabaseDriverFactory()
}

actual fun getPlatform(): Platform = JVMPlatform()
