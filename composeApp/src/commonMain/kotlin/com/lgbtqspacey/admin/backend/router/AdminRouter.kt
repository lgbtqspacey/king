package com.lgbtqspacey.admin.backend.router

import com.lgbtqspacey.admin.backend.model.Filter
import com.lgbtqspacey.admin.backend.model.FilterReports
import com.lgbtqspacey.admin.backend.model.Report
import com.lgbtqspacey.admin.backend.model.Role
import com.lgbtqspacey.admin.backend.model.User
import com.lgbtqspacey.admin.helpers.errorHandler
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

        suspend fun getUsers(filter: Filter, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.delete(Backend.Routes.Admin.USERS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    // todo: add query params
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

        suspend fun getRoles(filter: Filter, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.get(Backend.Routes.Admin.ROLES) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    // todo: add query params
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

        suspend fun getReports(filterReports: FilterReports, session: String): HttpResponse? {
            try {
                return Backend.CLIENT.get(Backend.Routes.Admin.REPORTS) {
                    headers.append(Backend.Headers.SESSION_TOKEN, session)
                    contentType(ContentType.Application.Json)
                    // todo: add query params
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
