package com.lgbtqspacey.king.features.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.lgbtqspacey.king.backend.adapter.AuthAdapter
import com.lgbtqspacey.king.commonMain.composeResources.Res
import com.lgbtqspacey.king.commonMain.composeResources.colaborators
import com.lgbtqspacey.king.commonMain.composeResources.`continue`
import com.lgbtqspacey.king.commonMain.composeResources.error
import com.lgbtqspacey.king.commonMain.composeResources.home
import com.lgbtqspacey.king.commonMain.composeResources.ic_account_circle
import com.lgbtqspacey.king.commonMain.composeResources.ic_file
import com.lgbtqspacey.king.commonMain.composeResources.ic_group
import com.lgbtqspacey.king.commonMain.composeResources.ic_home
import com.lgbtqspacey.king.commonMain.composeResources.ic_logout
import com.lgbtqspacey.king.commonMain.composeResources.ic_settings
import com.lgbtqspacey.king.commonMain.composeResources.ic_tag
import com.lgbtqspacey.king.commonMain.composeResources.logout
import com.lgbtqspacey.king.commonMain.composeResources.logout_confirmation
import com.lgbtqspacey.king.commonMain.composeResources.logout_confirmation_subtitle
import com.lgbtqspacey.king.commonMain.composeResources.no_back
import com.lgbtqspacey.king.commonMain.composeResources.reports
import com.lgbtqspacey.king.commonMain.composeResources.roles
import com.lgbtqspacey.king.commonMain.composeResources.settings
import com.lgbtqspacey.king.database.dao.UserDao
import com.lgbtqspacey.king.getPlatform
import com.lgbtqspacey.king.helpers.Dimensions
import com.lgbtqspacey.king.helpers.Screens
import com.lgbtqspacey.king.helpers.SideBarOption
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SideBarMenu(current: SideBarOption, navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("Nome") }
    var isSuccessfulLogout by remember { mutableStateOf(false) }
    var showConfirmLogout by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    /***********************
     * Conditional changes *
     ***********************/
    coroutineScope.launch {
        name = UserDao().getUser().name
    }

    if (showConfirmLogout) {
        Dialog(
            titleText = stringResource(Res.string.logout_confirmation),
            subtitleText = stringResource(Res.string.logout_confirmation_subtitle),
            confirmBtnText = stringResource(Res.string.logout),
            dismissBtnText = stringResource(Res.string.no_back),
            onConfirm = {
                coroutineScope.launch {
                    val logoutResult = AuthAdapter().logout()
                    if (logoutResult.isSuccess) {
                        isSuccessfulLogout = true
                    } else {
                        errorMessage = "${logoutResult.errorCode} - ${logoutResult.errorMessage}"
                        showConfirmLogout = false
                        showErrorDialog = true
                    }
                }
            },
            onDismiss = { showConfirmLogout = false }
        )
    }

    if (showErrorDialog) {
        ErrorDialog(
            titleText = stringResource(Res.string.error),
            errorMessage = errorMessage,
            btnText = stringResource(Res.string.`continue`),
            onDismiss = { showErrorDialog = false },
        )
    }

    if (isSuccessfulLogout) {
        navigator.navigate(Screens.APP)
    }

    /******
     * UI *
     *****/
    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxHeight()
            .wrapContentWidth()
            .widthIn(max = Dimensions.SIZE_192.dp())
    ) {
        val (
            profile,
            dividerTop,
            home,
            collaborators,
            roles,
            reports,
            dividerBottom,
            settings,
            logout,
            version,
        ) = createRefs()

        MenuOption(
            text = name,
            icon = Res.drawable.ic_account_circle,
            onClick = { navigator.navigate(Screens.PROFILE) },
            enabled = current !== SideBarOption.PROFILE,
            modifier = Modifier.constrainAs(profile) {
                top.linkTo(parent.top)
                start.linkTo(profile.start)
            }
        )

        HorizontalDivider(
            modifier = Modifier.constrainAs(dividerTop) {
                top.linkTo(profile.bottom)
                end.linkTo(parent.end)
            }
        )

        MenuOption(
            text = stringResource(Res.string.home),
            icon = Res.drawable.ic_home,
            onClick = { navigator.navigate(Screens.APP) },
            enabled = current !== SideBarOption.HOME,
            modifier = Modifier.constrainAs(home) {
                top.linkTo(dividerTop.bottom, Dimensions.SIZE_16.dp())
            }
        )

        MenuOption(
            text = stringResource(Res.string.colaborators),
            icon = Res.drawable.ic_group,
            onClick = { navigator.navigate(Screens.USERS) },
            enabled = current !== SideBarOption.USERS,
            modifier = Modifier.constrainAs(collaborators) {
                top.linkTo(home.bottom)
            }
        )

        MenuOption(
            text = stringResource(Res.string.roles),
            icon = Res.drawable.ic_tag,
            onClick = { navigator.navigate(Screens.ROLES) },
            enabled = current !== SideBarOption.ROLES,
            modifier = Modifier.constrainAs(roles) {
                top.linkTo(collaborators.bottom)
            }
        )

        MenuOption(
            text = stringResource(Res.string.reports),
            icon = Res.drawable.ic_file,
            onClick = { navigator.navigate(Screens.REPORTS) },
            enabled = current !== SideBarOption.REPORTS,
            modifier = Modifier.constrainAs(reports) {
                top.linkTo(roles.bottom)
            }
        )

        HorizontalDivider(
            modifier = Modifier.constrainAs(dividerBottom) {
                bottom.linkTo(settings.top)
                end.linkTo(settings.end)
            }
        )

        MenuOption(
            text = stringResource(Res.string.settings),
            icon = Res.drawable.ic_settings,
            enabled = current !== SideBarOption.SETTINGS,
            onClick = { navigator.navigate(Screens.SETTINGS) },
            modifier = Modifier.constrainAs(settings) {
                bottom.linkTo(logout.top)
            }
        )

        MenuOption(
            text = stringResource(Res.string.logout),
            icon = Res.drawable.ic_logout,
            onClick = {
                showConfirmLogout = true
            },
            modifier = Modifier.constrainAs(logout) {
                bottom.linkTo(version.top, Dimensions.SIZE_8.dp())
            }
        )

        Text(
            text = "v${getPlatform().version}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = Dimensions.SIZE_8.sp(),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(version) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
private fun MenuOption(
    text: String,
    icon: DrawableResource,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = { onClick() },
        border = null,
        enabled = enabled,
        modifier = modifier,
    ) {
        Icon(
            imageVector = vectorResource(icon),
            contentDescription = text,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = Dimensions.SIZE_8.dp())
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
