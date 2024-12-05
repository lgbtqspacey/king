package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.model.Session
import io.github.aakira.napier.Napier
import io.sentry.kotlin.multiplatform.Sentry

class TableSession(private val sharedDatabase: SharedDatabase) {
    suspend fun createSession(session: Session): Boolean {
        try {
            println("set token $session")
            sharedDatabase { db ->
                db.sessionQueries.insertSession(
                    token = session.token,
                    expiration = session.expiration,
                    userId = session.userId,
                )
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSession :: createSession", exception)
            Sentry.captureException(exception)
            return false
        }
    }

    suspend fun setUserInfo(session: Session): Boolean {
        try {
            println("set info: $session")
            sharedDatabase { db ->
                db.sessionQueries.insertUserInfo(
                    name = session.name,
                    accessLevel = session.accessLevel,
                    pronouns = session.pronouns,
                )
            }
            return true
        } catch (exception: Exception) {
            Napier.e("TableSession :: setUserInfo", exception)
            Sentry.captureException(exception)
            return false
        }
    }

    suspend fun getSession(): Session {
        var session = Session()
        try {
            sharedDatabase { db ->
                val data = db.sessionQueries.getSession().awaitAsOneOrNull()
                session = Session(
                    token = data?.token ?: "",
                    expiration = data?.expiration ?: "",
                    userId = data?.userId ?: "",
                    name = data?.name ?: "",
                    accessLevel = data?.accessLevel ?: "",
                    pronouns = data?.pronouns ?: "",
                )
            }
            println("get: $session")
        } catch (exception: Exception) {
            Napier.e("TableSession :: getSession", exception)
            Sentry.captureException(exception)
        }

        return session
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
