package com.lgbtqspacey.king

import android.os.Build

class AndroidPlatform() : Platform() {
    override val name: String = "Android_${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
