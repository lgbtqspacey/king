package com.lgbtqspacey.admin.features.composable

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.colaborators
import com.lgbtqspacey.admin.commonMain.composeResources.home
import com.lgbtqspacey.admin.commonMain.composeResources.ic_account_circle
import com.lgbtqspacey.admin.commonMain.composeResources.ic_file
import com.lgbtqspacey.admin.commonMain.composeResources.ic_group
import com.lgbtqspacey.admin.commonMain.composeResources.ic_home
import com.lgbtqspacey.admin.commonMain.composeResources.ic_logout
import com.lgbtqspacey.admin.commonMain.composeResources.ic_settings
import com.lgbtqspacey.admin.commonMain.composeResources.ic_tag
import com.lgbtqspacey.admin.commonMain.composeResources.logout
import com.lgbtqspacey.admin.commonMain.composeResources.logout_confirmation
import com.lgbtqspacey.admin.commonMain.composeResources.logout_confirmation_subtitle
import com.lgbtqspacey.admin.commonMain.composeResources.no_back
import com.lgbtqspacey.admin.commonMain.composeResources.reports
import com.lgbtqspacey.admin.commonMain.composeResources.roles
import com.lgbtqspacey.admin.commonMain.composeResources.settings
import com.lgbtqspacey.admin.getPlatform
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.Screens
import com.lgbtqspacey.admin.helpers.SideBarOption
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SideBarMenu(current: SideBarOption, navigator: Navigator) {
    var name by remember { mutableStateOf("Nome") }
    var showConfirmLogout by remember { mutableStateOf(false) }

    if (showConfirmLogout) {
        Dialog(
            titleText = stringResource(Res.string.logout_confirmation),
            subtitleText = stringResource(Res.string.logout_confirmation_subtitle),
            confirmBtnText = stringResource(Res.string.logout),
            dismissBtnText = stringResource(Res.string.no_back),
            onConfirm = { },
            onDismiss = { showConfirmLogout = false }
        )
    }

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
            onClick = { },
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
            onClick = { navigator.navigate("/app") },
            enabled = current !== SideBarOption.HOME,
            modifier = Modifier.constrainAs(home) {
                top.linkTo(dividerTop.bottom, Dimensions.SIZE_16.dp())
            }
        )

        MenuOption(
            text = stringResource(Res.string.colaborators),
            icon = Res.drawable.ic_group,
            onClick = { },
            modifier = Modifier.constrainAs(collaborators) {
                top.linkTo(home.bottom)
            }
        )

        MenuOption(
            text = stringResource(Res.string.roles),
            icon = Res.drawable.ic_tag,
            onClick = { },
            modifier = Modifier.constrainAs(roles) {
                top.linkTo(collaborators.bottom)
            }
        )

        MenuOption(
            text = stringResource(Res.string.reports),
            icon = Res.drawable.ic_file,
            onClick = { },
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
