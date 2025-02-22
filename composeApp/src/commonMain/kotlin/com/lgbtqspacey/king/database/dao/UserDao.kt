package com.lgbtqspacey.king.database.dao

import com.lgbtqspacey.king.database.Preferences
import com.lgbtqspacey.king.database.model.User
import com.lgbtqspacey.king.helpers.errorHandler

class UserDao {
    private val db = Preferences()

    private companion object {
        const val USER_NAME = "user_name"
        const val USER_ACCESS_LEVEL = "user_access_level"
        const val USER_PRONOUNS = "user_pronouns"
    }

    fun createOrUpdateUser(user: User): Boolean {
        try {
            db.insertData(USER_NAME, user.name)
            db.insertData(USER_ACCESS_LEVEL, user.accessLevel)
            db.insertData(USER_PRONOUNS, user.pronouns)

            return true
        } catch (exception: Exception) {
            errorHandler("UserDao :: insertUser", exception)
            return false
        }
    }

    fun getUser(): User {
        try {
            val name = db.getData(USER_NAME)
            val accessLevel = db.getData(USER_ACCESS_LEVEL)
            val pronouns = db.getData(USER_PRONOUNS)

            return User(name, accessLevel, pronouns)
        } catch (exception: Exception) {
            errorHandler("UserDao :: getUser", exception)
            return User()
        }
    }

    fun clearUser(): Boolean {
        try {
            db.deleteData(USER_NAME)
            db.deleteData(USER_ACCESS_LEVEL)
            db.deleteData(USER_PRONOUNS)

            return true
        } catch (exception: Exception) {
            errorHandler("UserDao :: clearUser", exception)
            return false
        }
    }
}
