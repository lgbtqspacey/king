package com.lgbtqspacey.admin.helpers

import io.github.aakira.napier.Napier
import io.sentry.Sentry

fun errorHandler(message: String, exception: Throwable) {
    Napier.e(message, exception)
    Sentry.captureException(exception)
}
