package com.lgbtqspacey.admin.features.people

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.backend.model.UserSummary
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.colaborators
import com.lgbtqspacey.admin.features.composable.SideBarMenu
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun Users(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    var isLoaded by remember { mutableStateOf(false) }
    var userList = mutableListOf<UserSummary>(
        UserSummary(
            id = "1234",
            name = "teste",
            pronouns = "ele",
            roles = "admin"
        ),
        UserSummary(
            id = "4567",
            name = "as",
            pronouns = "ela",
            roles = "ti"
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
            list,
            index,
            rows,
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

        /**
         * Animated view to show the data once it's loaded
         */
        AnimatedVisibility(
            visible = isLoaded,
            modifier = Modifier
                .constrainAs(listContainer) {
                    start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                    end.linkTo(parent.end)
                    top.linkTo(button.bottom, Dimensions.SIZE_16.dp())
                }
        ) {
            LazyColumn(
                modifier = Modifier.constrainAs(list) {
                    bottom.linkTo(parent.bottom, Dimensions.SIZE_16.dp())
                }
            ) {
                item {
                    Row(modifier = Modifier
                        .background(MaterialTheme.colorScheme.outline)
                        .constrainAs(index) {
                            start.linkTo(menu.end, Dimensions.SIZE_16.dp())
                        }
                    ) {
                        TableCell("ID")
                        TableCell("Nome")
                        TableCell("Pronomes")
                        TableCell("Cargos")
                    }
                }


                items(userList.count()) {
                    val user = userList[it]

                    Row(Modifier.constrainAs(rows) {
                        start.linkTo(index.start)
                        end.linkTo(index.end)
                    }) {
                        TableCell(user.id)
                        TableCell(user.name)
                        TableCell(user.pronouns)
                        TableCell(user.roles)
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(text: String) {
    val columnWeight = .2f // 20%

    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.onBackground)
            .weight(columnWeight)
            .padding(Dimensions.SIZE_8.dp())
    )
}

@Composable
fun RowScope.TableButton(text: String, onClick: () -> Unit) {
    // todo
}
