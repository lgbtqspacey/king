package com.lgbtqspacey.king.features.people

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.king.backend.adapter.AdminAdapter
import com.lgbtqspacey.king.backend.model.UserSummary
import com.lgbtqspacey.king.commonMain.composeResources.Res
import com.lgbtqspacey.king.commonMain.composeResources.colaborators
import com.lgbtqspacey.king.features.composable.SideBarMenu
import com.lgbtqspacey.king.features.composable.UserList
import com.lgbtqspacey.king.features.composable.UserListHeader
import com.lgbtqspacey.king.helpers.Dimensions
import com.lgbtqspacey.king.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun Users(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    var isLoaded by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var errorCode by remember { mutableStateOf("") }
    val userList = mutableListOf<UserSummary>()

    /** Conditional changes **/
    coroutineScope.launch {
        val users = AdminAdapter().getUsers()

        if (users.isSuccess) {
            users.usersSummarized?.data?.forEach { user ->
                val summary = UserSummary(
                    id = user.id ?: "",
                    accessLevel = user.accessLevel ?: "",
                    name = user.name ?: "",
                    pronouns = user.pronouns ?: ""
                )

                userList.add(summary)
            }
        } else {
            isError = true
            errorCode = users.errorCode.toString()
            errorMessage = users.errorMessage
        }

        isLoaded = true
    }

    /** UI **/
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val (
            menu,
            button,
            loading,
            header,
            listContainer,
            errorContainer
        ) = createRefs()

        Box(modifier = Modifier.constrainAs(menu) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            SideBarMenu(SideBarOption.USERS, navigator)
        }

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button) {
                start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                top.linkTo(parent.top, Dimensions.SIZE_16.dp())
            }
        ) {
            Text(stringResource(Res.string.colaborators))
        }

        /** Animated view to show loading circle while the application fetch the data **/
        AnimatedVisibility(
            visible = !isLoaded,
            modifier = Modifier
                .constrainAs(loading) {
                    start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                    top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        if (isLoaded) {
            Column(
                modifier = Modifier
                .padding(end = Dimensions.SIZE_16.dp())
                .constrainAs(header) {
                    start.linkTo(button.start)
                    top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                }) {
                UserListHeader()
            }

            LazyColumn(
                modifier = Modifier
                    .padding(end = Dimensions.SIZE_16.dp())
                    .constrainAs(listContainer) {
                        start.linkTo(button.start)
                        top.linkTo(header.bottom, Dimensions.SIZE_16.dp())
                    }) {
                items(userList.size) { user ->
                    UserList(navigator, userList[user])
                }
            }

            /** Error container **/
            if (isError) {
                Box(
                    modifier = Modifier
                        .constrainAs(errorContainer) {
                            start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                            top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .border(
                            color = MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(Dimensions.SIZE_4.dp()),
                            width = Dimensions.SIZE_1.dp()
                        )
                ) {
                    Text(
                        text = "$errorCode - $errorMessage",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(Dimensions.SIZE_16.dp())
                    )
                }
            }
        }
    }
}

