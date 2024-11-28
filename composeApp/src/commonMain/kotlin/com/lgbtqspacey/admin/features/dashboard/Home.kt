package com.lgbtqspacey.admin.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lgbtqspacey.admin.features.composable.SideBarMenu
import com.lgbtqspacey.admin.helpers.SideBarOption
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Home(navigator: Navigator) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
       SideBarMenu(SideBarOption.HOME, navigator)
    }
}
