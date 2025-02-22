package com.lgbtqspacey.king

class JVMPlatform : Platform() {
    override val name: String = "Desktop_Java-${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()
