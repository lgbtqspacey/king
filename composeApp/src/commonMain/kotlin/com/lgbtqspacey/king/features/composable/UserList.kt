package com.lgbtqspacey.king.features.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.king.backend.model.UserSummary
import com.lgbtqspacey.king.helpers.Dimensions
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun UserList(
    navigator: Navigator,
    user: UserSummary
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimensions.SIZE_32.dp())
            .background(MaterialTheme.colorScheme.background),
    ) {
        val (
            name,
            pronouns,
            accessLevel,
            details,
            divider,
        ) = createRefs()

        Text(
            text = user.name,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_192.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = user.pronouns,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_120.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(pronouns) {
                    top.linkTo(parent.top)
                    start.linkTo(name.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = user.accessLevel,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_192.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(accessLevel) {
                    top.linkTo(parent.top)
                    start.linkTo(pronouns.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(details) {
                    top.linkTo(parent.top)
                    start.linkTo(accessLevel.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text("Detalhes")
        }

        HorizontalDivider(
            modifier = Modifier.constrainAs(divider) {
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun UserListHeader() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimensions.SIZE_32.dp())
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        val (
            name,
            pronouns,
            accessLevel,
            details,
            divider,
        ) = createRefs()

        Text(
            text = "Nome",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_192.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = "Pronomes",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_120.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(pronouns) {
                    top.linkTo(parent.top)
                    start.linkTo(name.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = "Nível de acesso",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .wrapContentHeight()
                .width(Dimensions.SIZE_192.dp())
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(accessLevel) {
                    top.linkTo(parent.top)
                    start.linkTo(pronouns.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = "Detalhes",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(Dimensions.SIZE_4.dp())
                .constrainAs(details) {
                    top.linkTo(parent.top)
                    start.linkTo(accessLevel.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom)
                }
        )

        HorizontalDivider(
            modifier = Modifier.constrainAs(divider) {
                bottom.linkTo(parent.bottom)
            }
        )
    }
}
