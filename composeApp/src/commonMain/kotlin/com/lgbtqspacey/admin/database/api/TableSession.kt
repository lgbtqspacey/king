package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.database.Session
import io.github.aakira.napier.Napier

class TableSession(databaseDriver: DatabaseDriverFactory) {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    suspend fun createSession(session: Session) {
        try {
            sharedDatabase { db ->
                db.sessionQueries.insertSession(
                    token = session.token,
                    expiration = session.expiration,
                    userId = session.userId
                )
            }
        } catch (exception: Exception) {
            Napier.e("TableSession :: createSession", exception)
        }
    }

    suspend fun getSession(): Session {
        var session = Session("", "", "")
        try {
            sharedDatabase { db ->
                session = db.sessionQueries.getSession().awaitAsOne()
            }
        } catch (exception: Exception) {
            Napier.e("TableSession :: getSession", exception)
        }

        return session
    }

    suspend fun deleteSession() {
        try {
            sharedDatabase { db ->
                db.sessionQueries.deleteSession()
            }
        } catch (exception: Exception) {
            Napier.e("TableSession :: deleteSession", exception)
        }
    }
}
