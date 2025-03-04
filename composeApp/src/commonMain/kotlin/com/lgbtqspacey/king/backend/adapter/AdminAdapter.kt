package com.lgbtqspacey.king.backend.adapter

import com.lgbtqspacey.king.backend.model.ApiResult
import com.lgbtqspacey.king.backend.model.CreateItemResponse
import com.lgbtqspacey.king.backend.model.FilterDefault
import com.lgbtqspacey.king.backend.model.FilterReports
import com.lgbtqspacey.king.backend.model.FilterUser
import com.lgbtqspacey.king.backend.model.Report
import com.lgbtqspacey.king.backend.model.Role
import com.lgbtqspacey.king.backend.model.User
import com.lgbtqspacey.king.backend.router.AdminRouter
import com.lgbtqspacey.king.commonMain.composeResources.Res
import com.lgbtqspacey.king.commonMain.composeResources.something_went_wrong
import com.lgbtqspacey.king.database.dao.SessionDao
import com.lgbtqspacey.king.helpers.errorHandler
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.jetbrains.compose.resources.getString

class AdminAdapter {
    private val sessionDao = SessionDao()

    suspend fun createUser(user: User): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Users.createUser(user, session)

            when (response?.status) {
                HttpStatusCode.Created -> {
                    val createdId = response.body<CreateItemResponse>().id
                    result = ApiResult(
                        isSuccess = true,
                        response = hashMapOf(Pair("id", createdId))
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.Conflict -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: createUser", exception)
            return result
        }

        return result
    }

    suspend fun getUsers(filter: FilterUser? = null): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Users.getUsers(filter, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        responseDetails = response.body()
                    )
                }

                HttpStatusCode.BadRequest -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: getUsers", exception)
            return result
        }
        return result
    }

    suspend fun updateUser(userId: String, user: User): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Users.updateUser(userId, user, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        response = response.body()
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: updateUser", exception)
            return result
        }
        return result
    }

    suspend fun deleteUser(userId: String): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Users.deleteUser(userId, session)

            when (response?.status) {
                HttpStatusCode.NoContent -> {
                    result = ApiResult(true)
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: deleteUser", exception)
            return result
        }
        return result
    }

    suspend fun createRole(role: Role): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Roles.createRole(role, session)

            when (response?.status) {
                HttpStatusCode.Created -> {
                    val createdId = response.body<CreateItemResponse>().id
                    result = ApiResult(
                        isSuccess = true,
                        response = hashMapOf(Pair("id", createdId))
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.Conflict -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: createRole", exception)
            return result
        }

        return result
    }

    suspend fun getRoles(filter: FilterDefault): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Roles.getRoles(filter, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        response = response.body()
                    )
                }

                HttpStatusCode.BadRequest -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: getRoles", exception)
            return result
        }
        return result
    }

    suspend fun updateRole(roleId: String, role: Role): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Roles.updateRole(roleId, role, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        response = response.body()
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: updateRole", exception)
            return result
        }
        return result
    }

    suspend fun deleteRole(roleId: String): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Roles.deleteRole(roleId, session)

            when (response?.status) {
                HttpStatusCode.NoContent -> {
                    result = ApiResult(true)
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: deleteRole", exception)
            return result
        }
        return result
    }

    suspend fun createReport(report: Report): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Reports.createReport(report, session)

            when (response?.status) {
                HttpStatusCode.Created -> {
                    val createdId = response.body<CreateItemResponse>().id
                    result = ApiResult(
                        isSuccess = true,
                        response = hashMapOf(Pair("id", createdId))
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.Conflict -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: createReport", exception)
            return result
        }

        return result
    }

    suspend fun getReports(filter: FilterReports): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Reports.getReports(filter, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        response = response.body()
                    )
                }

                HttpStatusCode.BadRequest -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: getReports", exception)
            return result
        }
        return result
    }

    suspend fun updateReport(reportId: String, report: Report): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Reports.updateReport(reportId, report, session)

            when (response?.status) {
                HttpStatusCode.OK -> {
                    result = ApiResult(
                        isSuccess = true,
                        response = response.body()
                    )
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: updateReport", exception)
            return result
        }
        return result
    }

    suspend fun deleteReport(reportId: String): ApiResult {
        var result = ApiResult(false, 500, getString(Res.string.something_went_wrong))

        try {
            val session = sessionDao.getSession().token
            val response = AdminRouter.Reports.deleteReport(reportId, session)

            when (response?.status) {
                HttpStatusCode.NoContent -> {
                    result = ApiResult(true)
                }

                HttpStatusCode.BadRequest,
                HttpStatusCode.NotFound -> {
                    result = ApiResult(
                        isSuccess = false,
                        errorCode = response.status.value,
                        errorMessage = response.bodyAsText()
                    )
                }

                else -> {
                    return result
                }
            }
        } catch (exception: Exception) {
            errorHandler("AdminAdapter :: deleteReport", exception)
            return result
        }
        return result
    }
}
