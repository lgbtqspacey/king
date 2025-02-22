package com.lgbtqspacey.king.database.dao

import com.lgbtqspacey.king.database.Preferences
import com.lgbtqspacey.king.database.model.Session
import com.lgbtqspacey.king.helpers.errorHandler

class SessionDao {
    private val db = Preferences()

    private companion object {
        const val SESSION_TOKEN = "session_token"
        const val SESSION_EXPIRATION = "session_expiration"
        const val SESSION_USER_ID = "session_user_id"
    }

    fun createOrUpdateSession(session: Session): Boolean {
        try {
            db.insertData(SESSION_TOKEN, session.token)
            db.insertData(SESSION_EXPIRATION, session.expiration)
            db.insertData(SESSION_USER_ID, session.userId)

            return true
        } catch (exception: Exception) {
            errorHandler("SessionDao :: insertSession", exception)
            return false
        }
    }

    fun getSession(): Session {
        try {
            val token = db.getData(SESSION_TOKEN)
            val expiration = db.getData(SESSION_EXPIRATION)
            val userId = db.getData(SESSION_USER_ID)

            return Session(token, expiration, userId)
        } catch (exception: Exception) {
            errorHandler("SessionDao :: getSession", exception)
            return Session()
        }
    }

    fun clearSession(): Boolean {
        try {
            db.deleteData(SESSION_TOKEN)
            db.deleteData(SESSION_EXPIRATION)
            db.deleteData(SESSION_USER_ID)

            return true
        } catch (exception: Exception) {
            errorHandler("SessionDao :: clearSession", exception)
            return false
        }
    }

}
