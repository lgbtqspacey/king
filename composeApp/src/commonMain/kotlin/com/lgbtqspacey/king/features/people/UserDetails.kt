package com.lgbtqspacey.king.features.people

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.lgbtqspacey.king.backend.adapter.AdminAdapter
import com.lgbtqspacey.king.backend.model.FilterUser
import com.lgbtqspacey.king.backend.model.User
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun UserDetails(navigator: Navigator, userId: String) {
    val coroutineScope = rememberCoroutineScope()

    var isLoaded by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var errorCode by remember { mutableStateOf("") }
    val userDetails = mutableListOf<User>()

    coroutineScope.launch {
        val search = AdminAdapter().getUsers(FilterUser(id = userId))

        if (search.isSuccess) {
            search.userDetails?.data?.forEach { user ->
                val details = User(
                    id = user.id ?: "",
                    pronouns = user.pronouns ?: "",
                    accessLevel = user.accessLevel ?: "",
                    email = user.email ?: "",
                    phone = user.phone ?: "",
                    discordId = user.discordId ?: "",
                    username = user.username ?: "",
                    leftAt = user.leftAt ?: "",
                    joinedAt = user.joinedAt ?: "",
                    createdBy = user.createdBy ?: "",
                    dateOfBirth = user.dateOfBirth ?: "",
                    name = user.name ?: "",
                    teams = user.teams
                )

                userDetails.add(details)
            }
        } else {
            isError = true
            errorCode = search.errorCode.toString()
            errorMessage = search.errorMessage
        }

        isLoaded = true
    }

    AnimatedVisibility(
        visible = isLoaded,
        modifier = Modifier
    ) {

    }
}
