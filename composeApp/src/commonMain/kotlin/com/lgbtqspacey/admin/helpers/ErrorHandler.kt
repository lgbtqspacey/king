package com.lgbtqspacey.admin.helpers

import io.github.aakira.napier.Napier
import io.sentry.Sentry

fun errorHandler(tag: String, exception: Throwable) {
    val msg = "$tag :: ${exception.message}"

    Napier.e(msg, exception)
    Sentry.captureException(exception)
}
