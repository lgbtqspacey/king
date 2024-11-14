package com.lgbtqspacey.admin

import android.app.Application
import android.os.Build
import com.lgbtqspacey.admin.database.DatabaseDriverFactory

class AndroidPlatform : Platform() {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val databaseDriver: DatabaseDriverFactory = DatabaseDriverFactory(Application().applicationContext)
}

actual fun getPlatform(): Platform = AndroidPlatform()
