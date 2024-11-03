package com.lgbtqspacey.admin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform