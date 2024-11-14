package com.lgbtqspacey.admin.database.api

import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.lgbtqspacey.admin.database.DatabaseDriverFactory
import com.lgbtqspacey.admin.database.SharedDatabase
import com.lgbtqspacey.admin.database.dao.SessionDao
import com.lgbtqspacey.database.Session

class SessionDaoImpl(databaseDriver: DatabaseDriverFactory) : SessionDao {
    private val sharedDatabase = SharedDatabase(databaseDriver)

    override suspend fun createSession(session: Session) {
        sharedDatabase { db ->
            db.sessionQueries.insertSession(
                token = session.token,
                expiration = session.expiration,
                userId = session.userId
            )
        }
    }

    override suspend fun getSession(): Session {
        var session = Session("", "", "")

        sharedDatabase { db ->
            session = db.sessionQueries.getSession().awaitAsOne()
        }

        return session
    }

    override suspend fun deleteSession() {
        sharedDatabase { db ->
            db.sessionQueries.deleteSession()
        }
    }
}
