package com.lgbtqspacey.admin

import android.os.Build
import com.lgbtqspacey.admin.database.DatabaseDriverFactory

class AndroidPlatform() : Platform() {
    override val name: String = "Android_${Build.VERSION.SDK_INT}"
    override val databaseDriver: DatabaseDriverFactory = MainActivity().driver
}

actual fun getPlatform(): Platform = AndroidPlatform()
