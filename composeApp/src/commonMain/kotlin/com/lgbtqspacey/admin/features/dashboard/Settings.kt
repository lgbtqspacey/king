package com.lgbtqspacey.admin.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.commonMain.composeResources.*
import com.lgbtqspacey.admin.database.api.TableSettings
import com.lgbtqspacey.admin.features.composable.SideBarMenu
import com.lgbtqspacey.admin.getPlatform
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.Screens
import com.lgbtqspacey.admin.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun Settings(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()
    var isDarkMode by remember { mutableStateOf(true) }

    val toggleDarkMode: () -> Unit = {
        coroutineScope.launch {
            TableSettings(getPlatform().databaseDriver).toggleDarkMode()
        }
        isDarkMode = !isDarkMode
    }

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

        Text(
            text = if (isDarkMode) {
                stringResource(Res.string.toggle_light_mode)
            } else {
                stringResource(Res.string.toggle_dark_mode)
            },
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.constrainAs(toggleDarkModeLabel) {
                start.linkTo(menu.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        Switch(
            checked = isDarkMode,
            onCheckedChange = { toggleDarkMode() },
            modifier = Modifier
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(toggleDarkModeSwitch) {
                    start.linkTo(menu.end)
                    end.linkTo(parent.end)
                    top.linkTo(toggleDarkModeLabel.bottom)
                },
            thumbContent = {
                if (isDarkMode) {
                    Icon(
                        vectorResource(Res.drawable.ic_dark_mode),
                        stringResource(Res.string.toggle_light_mode),
                        Modifier.size(Dimensions.SIZE_12.dp())
                    )
                } else {
                    Icon(
                        vectorResource(Res.drawable.ic_light_mode),
                        stringResource(Res.string.toggle_dark_mode),
                        Modifier.size(Dimensions.SIZE_12.dp())
                    )
                }
            }
        )
    }
}
