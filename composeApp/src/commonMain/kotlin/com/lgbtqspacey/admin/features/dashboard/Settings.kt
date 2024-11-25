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
import com.lgbtqspacey.admin.commonMain.composeResources.*
import com.lgbtqspacey.admin.database.api.TableSettings
import com.lgbtqspacey.admin.getPlatform
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.Screens
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

    /**
     * Screen container
     */
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .padding(top = Dimensions.SIZE_80.dp())
                .align(Alignment.TopCenter)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { navigator.navigate(Screens.DASHBOARD) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimensions.SIZE_16.dp())
            ) {
                Text(stringResource(Res.string.dashboard))
            }

            /**
             * Toggle dark mode
             */
            Text(
                text = if (isDarkMode) {
                    stringResource(Res.string.toggle_light_mode)
                } else {
                    stringResource(Res.string.toggle_dark_mode)
                },
                color = MaterialTheme.colorScheme.onBackground
            )

            Switch(
                checked = isDarkMode,
                onCheckedChange = { toggleDarkMode() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimensions.SIZE_4.dp()),
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
}
