package com.lgbtqspacey.admin.features.people

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.features.composable.SideBarMenu
import com.lgbtqspacey.admin.helpers.SideBarOption
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Users(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val (
            menu,
        ) = createRefs()

        Box(modifier = Modifier.constrainAs(menu) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            SideBarMenu(SideBarOption.USERS, navigator)
        }
    }
}
