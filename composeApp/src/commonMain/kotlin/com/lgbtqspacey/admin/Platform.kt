package com.lgbtqspacey.admin

import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import io.sentry.kotlin.multiplatform.Sentry

abstract class  Platform {
    open val name: String = ""
    open val version: String = "1.0.0"
    abstract val databaseDriver: DatabaseDriverFactory

    fun initSentry(platform: String, version: String) {
        Sentry.init { options ->
            options.dsn = Secrets.SENTRY_DNS
            options.release = "${platform}_v${version}"
            options.tracesSampleRate = 1.0
            options.debug = true
        }
    }
}

expect fun getPlatform(): Platform
