package com.lgbtqspacey.admin

abstract class  Platform {
    open val name: String = ""
    open val version: String = "1.0.0"
}

expect fun getPlatform(): Platform
