package com.lgbtqspacey.admin

interface Platform {
    val name: String
    val version: String
}

expect fun getPlatform(): Platform
