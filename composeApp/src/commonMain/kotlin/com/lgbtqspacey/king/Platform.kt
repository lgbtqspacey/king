package com.lgbtqspacey.king

import io.sentry.kotlin.multiplatform.Sentry

abstract class Platform() {
    open val name: String = ""
    open val version: String = "1.0.0"

    fun initSentry(version: String) {
        Sentry.init { options ->
            options.dsn = Secrets.SENTRY_DNS
            options.release = "v$version"
            options.tracesSampleRate = 1.0
            options.environment = "debug"
            options.beforeSend = { event ->
                event.serverName = "windows-pc"
                event
            }
        }
    }
}

expect fun getPlatform(): Platform
