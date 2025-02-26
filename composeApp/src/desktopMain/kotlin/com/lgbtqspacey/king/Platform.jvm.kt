package com.lgbtqspacey.king

class JVMPlatform : Platform() {
    override val name: String = "JVM"
}

actual fun getPlatform(): Platform = JVMPlatform()
