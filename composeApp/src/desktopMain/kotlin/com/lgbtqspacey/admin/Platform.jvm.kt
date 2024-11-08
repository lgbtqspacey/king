package com.lgbtqspacey.admin

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val version: String = "1.0.0"
}

actual fun getPlatform(): Platform = JVMPlatform()
