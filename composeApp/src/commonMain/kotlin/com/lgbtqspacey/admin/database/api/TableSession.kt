package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.model.Session
import com.lgbtqspacey.admin.helpers.errorHandler

class TableSession(private val sharedDatabase: SharedDatabase) {
    suspend fun createSession(session: Session): Boolean {
        try {
            sharedDatabase { db ->
                db.sessionQueries.updateToken(
                    token = session.token,
                    expiration = session.expiration,
                    userId = session.userId,
                )
            }
            return true
        } catch (exception: Exception) {
            errorHandler("TableSession :: createSession", exception)
            return false
        }
    }

    suspend fun setUserInfo(session: Session): Boolean {
        try {
            sharedDatabase { db ->
                db.sessionQueries.updateUserInfo(
                    name = session.name,
                    accessLevel = session.accessLevel,
                    pronouns = session.pronouns,
                )
            }
            return true
        } catch (exception: Exception) {
            errorHandler("TableSession :: setUserInfo", exception)
            return false
        }
    }

    suspend fun getSession(): Session {
        var session = Session()
        try {
            sharedDatabase { db ->
                val data = db.sessionQueries.getSession().awaitAsOne()
                session = Session(
                    token = data.token!!,
                    expiration = data.expiration!!,
                    userId = data.userId!!,
                    name = data.name!!,
                    accessLevel = data.accessLevel!!,
                    pronouns = data.pronouns!!,
                )
            }
        } catch (exception: Exception) {
            errorHandler("TableSession :: getSession", exception)
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
            errorHandler("TableSession :: deleteSession", exception)
            return false
        }
    }
}
