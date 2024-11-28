package com.lgbtqspacey.admin.features.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.colaborators
import com.lgbtqspacey.admin.commonMain.composeResources.dashboard
import com.lgbtqspacey.admin.commonMain.composeResources.logout
import com.lgbtqspacey.admin.commonMain.composeResources.reports
import com.lgbtqspacey.admin.commonMain.composeResources.roles
import com.lgbtqspacey.admin.commonMain.composeResources.settings
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.Screens
import com.lgbtqspacey.admin.helpers.SideBarOption
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun SideBarMenu(current: SideBarOption, navigator: Navigator) {
    val confirmLogout: () -> Unit = {
        // todo: show dialog
    }

    Column(
        modifier = Modifier
            .padding(top = Dimensions.SIZE_80.dp())
            .background(MaterialTheme.colorScheme.inversePrimary)
            .fillMaxHeight()
    ) {
        /**
         * Home page
         */
        Button(
            onClick = { navigator.navigate(Screens.HOME) },
            enabled = current !== SideBarOption.HOME,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = Dimensions.SIZE_16.dp()),
        ) {
            Text(stringResource(Res.string.dashboard))
        }

        /**
         * Settings
         */
        Button(
            onClick = { navigator.navigate(Screens.SETTINGS) },
            enabled = current !== SideBarOption.SETTINGS,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimensions.SIZE_16.dp())
        ) {
            Text(stringResource(Res.string.settings))
        }

        /**
         * Users
         */
        Button(
            onClick = { navigator.navigate(Screens.USERS) },
            enabled = current !== SideBarOption.USERS,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimensions.SIZE_16.dp())
        ) {
            Text(stringResource(Res.string.colaborators))
        }

        /**
         * Reports
         */
        Button(
            onClick = { navigator.navigate(Screens.REPORTS) },
            enabled = current !== SideBarOption.REPORTS,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimensions.SIZE_16.dp())
        ) {
            Text(stringResource(Res.string.reports))
        }

        /**
         * Roles
         */
        Button(
            onClick = { navigator.navigate(Screens.ROLES) },
            enabled = current !== SideBarOption.ROLES,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimensions.SIZE_16.dp())
        ) {
            Text(stringResource(Res.string.roles))
        }

        /**
         * Logout
         */
        Button(
            onClick = { confirmLogout() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimensions.SIZE_16.dp())
        ) {
            Text(stringResource(Res.string.logout))
        }
    }
}
