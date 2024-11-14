package com.lgbtqspacey.admin.database.dao

import com.lgbtqspacey.database.Session

interface SessionDao {
    suspend fun createSession(session: Session)

    suspend fun getSession(): Session

    suspend fun deleteSession()
}
