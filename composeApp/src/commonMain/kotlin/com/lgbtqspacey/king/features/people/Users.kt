package com.lgbtqspacey.king.features.people

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.king.backend.model.UserSummary
import com.lgbtqspacey.king.commonMain.composeResources.Res
import com.lgbtqspacey.king.commonMain.composeResources.colaborators
import com.lgbtqspacey.king.features.composable.SideBarMenu
import com.lgbtqspacey.king.features.composable.UserList
import com.lgbtqspacey.king.helpers.Dimensions
import com.lgbtqspacey.king.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun Users(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    var isLoaded by remember { mutableStateOf(false) }
    var userList = mutableListOf(
        UserSummary(
            id = "1234",
            name = "12345678901234567890",
            pronouns = "123456",
            accessLevel = "123456"
        ),
        UserSummary(
            id = "4567",
            name = "asaaaaaaaaaaaaaaaaaa",
            pronouns = "ela",
            accessLevel = "editor"
        )
    )

    /***********************
     * Conditional changes *
     ***********************/
    coroutineScope.launch {
        isLoaded = true
    }

    /******
     * UI *
     *****/
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val (
            menu,
            button,
            loading,
            listContainer,
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

        /**
         * Animated view to show loading circle while the application fetch the data
         */
        AnimatedVisibility(
            visible = !isLoaded,
            modifier = Modifier
                .constrainAs(loading) {
                    start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                    top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                }
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(end = Dimensions.SIZE_16.dp())
                .constrainAs(listContainer) {
                    start.linkTo(button.start)
                    top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                }) {
            items(userList.size) { user ->
                UserList(navigator, userList[user])
            }
        }
    }
}

