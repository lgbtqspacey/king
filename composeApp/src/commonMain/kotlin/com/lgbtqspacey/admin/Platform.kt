package com.lgbtqspacey.admin

import io.sentry.kotlin.multiplatform.Sentry

abstract class Platform() {
    open val name: String = ""
    open val version: String = "1.0.0"

    fun initSentry(platform: String, version: String) {
        Sentry.init { options ->
            options.dsn = Secrets.SENTRY_DNS
            options.release = "${platform}_v${version}"
            options.tracesSampleRate = 1.0
        }
    }
}

expect fun getPlatform(): Platform
