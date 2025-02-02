package com.lgbtqspacey.admin.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.database.Preferences
import com.lgbtqspacey.admin.features.composable.SideBarMenu
import com.lgbtqspacey.admin.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Settings(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val (
            menu,
            toggleDarkModeLabel,
            toggleDarkModeSwitch,
        ) = createRefs()

        Box(modifier = Modifier.constrainAs(menu) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            SideBarMenu(SideBarOption.SETTINGS, navigator)
        }

        Button(onClick = {
            coroutineScope.launch {
                Preferences().insertData("teste", "value")
            }
        },
            modifier = Modifier.constrainAs(toggleDarkModeLabel) {
                start.linkTo(menu.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }) {
            Text("set")
        }

        Button(onClick = {
            coroutineScope.launch {
                val data = Preferences().getData("teste")
                println(data)
            }
        },
            modifier = Modifier.constrainAs(toggleDarkModeSwitch) {
                start.linkTo(menu.end)
                end.linkTo(parent.end)
                top.linkTo(toggleDarkModeLabel.bottom)
            }) {
            Text("get")
        }

//        Text(
//            text = if (isDarkMode.value) {
//                stringResource(Res.string.toggle_light_mode)
//            } else {
//                stringResource(Res.string.toggle_dark_mode)
//            },
//            color = MaterialTheme.colorScheme.onBackground,
//            modifier = Modifier.constrainAs(toggleDarkModeLabel) {
//                start.linkTo(menu.end)
//                end.linkTo(parent.end)
//                top.linkTo(parent.top)
//            }
//        )
//
//        Switch(
//            checked = isDarkMode.value,
//            onCheckedChange = { darkMode() },
//            modifier = Modifier
//                .padding(Dimensions.SIZE_4.dp())
//                .constrainAs(toggleDarkModeSwitch) {
//                    start.linkTo(menu.end)
//                    end.linkTo(parent.end)
//                    top.linkTo(toggleDarkModeLabel.bottom)
//                },
//            thumbContent = {
//                if (isDarkMode.value) {
//                    Icon(
//                        vectorResource(Res.drawable.ic_dark_mode),
//                        stringResource(Res.string.toggle_light_mode),
//                        Modifier.size(Dimensions.SIZE_12.dp())
//                    )
//                } else {
//                    Icon(
//                        vectorResource(Res.drawable.ic_light_mode),
//                        stringResource(Res.string.toggle_dark_mode),
//                        Modifier.size(Dimensions.SIZE_12.dp())
//                    )
//                }
//            }
//        )
    }
}
