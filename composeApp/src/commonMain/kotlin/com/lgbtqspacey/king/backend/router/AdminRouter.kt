package com.lgbtqspacey.king.backend.router

import com.lgbtqspacey.king.backend.model.FilterDefault
import com.lgbtqspacey.king.backend.model.FilterReports
import com.lgbtqspacey.king.backend.model.FilterUser
import com.lgbtqspacey.king.backend.model.Report
import com.lgbtqspacey.king.backend.model.Role
import com.lgbtqspacey.king.backend.model.User
import com.lgbtqspacey.king.helpers.errorHandler
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class AdminRouter {
    object Users {
        suspend fun createUser(user: User, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.post(Backend.Routes.Admin.USERS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(user)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: createUser", exception)
                return null
            }
        }

        suspend fun getUsers(filter: FilterUser? = null, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.get(Backend.Routes.Admin.USERS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)

                    val isValidPage = filter?.page !== null && filter.page > 0
                    val isValidLimit = filter?.limit !== null && filter.limit > 0

                    if (isValidPage)
                        url.parameters.append(Backend.Query.PAGE, filter?.page.toString())
                    if (isValidLimit)
                        url.parameters.append(Backend.Query.LIMIT, filter?.limit.toString())
                    if (!filter?.id.isNullOrEmpty())
                        url.parameters.append(Backend.Query.ID, filter?.id!!)
                    if (!filter?.email.isNullOrEmpty())
                        url.parameters.append(Backend.Query.EMAIL, filter?.email!!)
                    if (!filter?.discordId.isNullOrEmpty())
                        url.parameters.append(Backend.Query.DISCORD_ID, filter?.discordId!!)
                    if (!filter?.username.isNullOrEmpty())
                        url.parameters.append(Backend.Query.USERNAME, filter?.username!!)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: getUsers", exception)
                return null
            }
        }

        suspend fun updateUser(id: String, user: User, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.patch(Backend.Routes.Admin.USERS) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(user)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: updateUser", exception)
                return null
            }
        }

        suspend fun deleteUser(id: String, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.delete(Backend.Routes.Admin.USERS) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: deleteUser", exception)
                return null
            }
        }
    }

    object Roles {
        suspend fun createRole(role: Role, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.post(Backend.Routes.Admin.ROLES) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(role)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: createRole", exception)
                return null
            }
        }

        suspend fun getRoles(filter: FilterDefault, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.get(Backend.Routes.Admin.ROLES) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)

                    val isValidPage = filter.page !== null && filter.page > 0
                    val isValidLimit = filter.limit !== null && filter.limit > 0

                    if (isValidPage)
                        url.parameters.append(Backend.Query.PAGE, filter.page.toString())
                    if (isValidLimit)
                        url.parameters.append(Backend.Query.LIMIT, filter.limit.toString())
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: getRoles", exception)
                return null
            }
        }

        suspend fun updateRole(id: String, role: Role, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.put(Backend.Routes.Admin.ROLES) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(role)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: updateRole", exception)
                return null
            }
        }

        suspend fun deleteRole(id: String, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.delete(Backend.Routes.Admin.ROLES) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: deleteRole", exception)
                return null
            }
        }
    }

    object Reports {
        suspend fun createReport(report: Report, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.post(Backend.Routes.Admin.REPORTS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(report)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: createReport", exception)
                return null
            }
        }

        suspend fun getReports(filter: FilterReports, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.get(Backend.Routes.Admin.REPORTS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    url.parameters.append(Backend.Query.USER_ID, filter.userId)

                    val isValidPage = filter.page !== null && filter.page > 0
                    val isValidLimit = filter.limit !== null && filter.limit > 0

                    if (isValidPage)
                        url.parameters.append(Backend.Query.PAGE, filter.page.toString())
                    if (isValidLimit)
                        url.parameters.append(Backend.Query.LIMIT, filter.limit.toString())
                    if (!filter.to.isNullOrEmpty())
                        url.parameters.append(Backend.Query.TO, filter.to)
                    if (!filter.from.isNullOrEmpty())
                        url.parameters.append(Backend.Query.FROM, filter.from)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: getReports", exception)
                return null
            }
        }

        suspend fun updateReport(id: String, report: Report, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.put(Backend.Routes.Admin.REPORTS) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    setBody(report)

                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: updateReports", exception)
                return null
            }
        }

        suspend fun deleteReport(id: String, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.delete(Backend.Routes.Admin.REPORTS) {
                    url.appendPathSegments(id)
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                }
            } catch (exception: Exception) {
                errorHandler("AdminRouter :: deleteReport", exception)
                return null
            }
        }
    }
}
