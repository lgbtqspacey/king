package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.database.Session
import io.github.aakira.napier.Napier
import io.sentry.kotlin.multiplatform.Sentry

class TableSession(databaseDriver: DatabaseDriverFactory) {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    suspend fun createSession(session: Session): Boolean {
        try {
            sharedDatabase { db ->
                db.sessionQueries.insertSession(
                    token = session.token,
                    expiration = session.expiration,
                    userId = session.userId
                )
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSession :: createSession", exception)
            Sentry.captureException(exception)
            return false
        }
    }

    suspend fun getSession(): Session {
        var session: Session? = null
        try {
            sharedDatabase { db ->
                session = db.sessionQueries.getSession().awaitAsOneOrNull()
            }
        } catch (exception: Exception) {
            Napier.e("TableSession :: getSession", exception)
            Sentry.captureException(exception)
        }

        return session ?: Session("", "", "")
    }

    suspend fun deleteSession(): Boolean {
        try {
            sharedDatabase { db ->
                db.sessionQueries.deleteSession()
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSession :: deleteSession", exception)
            Sentry.captureException(exception)
            return false
        }
    }
}
